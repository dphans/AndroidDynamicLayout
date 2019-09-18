import argparse
import os
import xml.etree.ElementTree as ET
import json
import entities


parser = argparse.ArgumentParser(
    description='Android Dynamic Layout layout converter',
)
sub_parser = parser.add_subparsers()

convert_source = sub_parser.add_parser(
    name='convert',
    help='Convert XML layout into other format.',
)
convert_source.add_argument(
    '--input',
    help='Path to source XML file for parsing contents.',
)
convert_source.add_argument(
    '--output',
    nargs='*',
    help='Path to output files for exporting contents.',
)
convert_source.set_defaults(
    command='convert',
)


def parse_recursive(element, namespaces):
    widget = entities.Widget()
    widget.view_class = entities.Widget.get_view_class(name=element.tag,)

    def normalize_attr(name):
        if name[0] == "{":
            uri, tag = name[1:].split("}")
            if uri in namespaces.keys():
                ns = namespaces[uri]
                return '%s:%s' % (ns, tag)
            return tag
        else:
            return name

    if isinstance(element.attrib, dict):
        widget.attributes = [entities.Attribute(name=normalize_attr(attr_key), value=element.attrib.get(attr_key))
                             for attr_key in element.attrib.keys()]
    else:
        widget.attributes = []

    widget.childrens = [parse_recursive(element=child_element, namespaces=namespaces, ) for child_element in element]
    return widget


def parser_output_json(document, output):
    json_data = json.dumps(
        document,
        default=lambda o: o.__dict__,
        sort_keys=True,
        ensure_ascii=False,
        indent=2,
    )

    with open(output, 'w') as output_file:
        output_file.write(json_data)
        output_file.flush()
        output_file.close()


output_parsers = {
    '.json': parser_output_json
}


def src_xml_to_dest_paths(src_xml, dests):
    if not os.path.exists(src_xml):
        print 'Input layout xml file not exist at path:', src_xml
        return
    if len(dests) <= 0:
        print 'Please define output paths for exporting contents!'
        return

    tree = ET.parse(source=src_xml, )
    namespaces = dict([(node[1], str(node[0])) for (_, node) in ET.iterparse(src_xml, events=['start-ns'])])
    root = tree.getroot()

    # parsed root widget
    widget = parse_recursive(element=root, namespaces=namespaces, )

    # skip for databinding layouts
    if widget.view_class == 'layout':
        print 'Databinding layouts currently not supported!'
        return

    # create document
    document = entities.Document.create_with_root(
        widget=widget,
        namespaces=namespaces,
    )

    # checking output files
    for output_path in dests:
        _, ext = os.path.splitext(output_path)

        if ext not in output_parsers.keys():
            print 'Extension %s currently not supported!'
            continue

        output_parsers[ext](document, output_path)


def print_widget(widget, level=0):
    if not widget:
        return
    if not isinstance(widget, entities.Widget):
        return

    tags = ''.join(['\t' for _ in range(level)])

    print '%s\033[94m%s:\033[0m' % (tags, widget.view_class)

    for attr in widget.attributes:
        print '%s -%s=%s' % (tags, attr.name, attr.value)

    [print_widget(widget=child, level=level + 1) for child in widget.childrens]


if __name__ == '__main__':
    args = parser.parse_args()

    if args.command == 'convert':
        input_src = args.input
        paths = args.output
        if not paths:
            dest_paths = []
        src_xml_to_dest_paths(
            src_xml=input_src,
            dests=paths,
        )
