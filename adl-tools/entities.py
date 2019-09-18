import consts
from uuid import uuid4


class Attribute:
    key = ''
    name = ''
    namespace = None
    type = 'UNKNOWN'
    value = ''

    def __init__(self, name, value):
        self.name = name
        self.value = value
        self.__parse_name(name=name,)
        self.__parse_value_type(value=value,)

    def __parse_name(self, name):
        comps = name.split(':')
        self.namespace = None

        if len(comps) > 1:
            self.namespace = comps[0]
            self.key = comps[-1]
        if len(comps) == 1:
            self.key = comps[0]

    def __parse_value_type(self, value):
        if not value or type(value) is not str:
            self.type = 'Null'
        if value.lower() == '@null':
            self.type = 'Null'
        elif value.startswith('@'):
            self.type = 'Resource'
        elif value.startswith('?'):
            self.type = 'Attribute'
        elif value.endswith(('dp', 'in', 'mm', 'pt', 'px', 'sp')):
            self.type = 'Dimension'
        else:
            self.type = 'String'


class Widget:
    _uid = None
    view_class = ''
    attributes = []
    childrens = []

    def __init__(self):
        self._uid = uuid4().hex[:20].upper()
        self.view_class = ''
        self.attributes = []
        self.childrens = []

    @staticmethod
    def get_view_class(name):
        if len('.'.split(name)) > 1:
            return name

        for android_class in consts.android_view_classes:
            if name in consts.android_view_classes[android_class]:
                return '%s.%s' % (android_class, name)
        return name


class Document:
    view = None
    xmlns = {}
    view_ids = []
    view_classes = []
    version = consts.parser_version

    def __init__(self):
        self.view = None
        self.xmlns = {}
        self.view_classes = []
        self.version = consts.parser_version


    @staticmethod
    def create_with_root(widget, namespaces):
        doc = Document()
        doc.view = widget
        doc.xmlns = {ns[1]: ns[0] for ns in namespaces.items()}
        doc.view_classes, doc.view_ids = Document.__parse_view_classes(widget=widget, )
        return doc

    @staticmethod
    def __parse_view_classes(widget, current_classes=None, current_ids=None):
        if current_classes is None:
            current_classes = []
        if current_ids is None:
            current_ids = []

        if widget.view_class not in current_classes:
            current_classes.append(widget.view_class)

        for attr in widget.attributes:
            if attr.key == 'id' and attr.value.startswith(('@id', '@+id',)):
                if attr.value not in current_ids:
                    current_ids.append(attr.value)
                break

        for child in widget.childrens:
            Document.__parse_view_classes(
                widget=child,
                current_classes=current_classes,
                current_ids=current_ids,
            )

        return current_classes, current_ids
