# AndroidDynamicLayout (ADL)
Flexible android layouts with high customization.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![JitPack](https://jitpack.io/v/dphans/AndroidDynamicLayout.svg)](https://jitpack.io/#dphans/AndroidDynamicLayout/0.0.1)

For more information and documentation please see the [website](http://dphans.github.io/pages/library/adl/).


## Purposes

Basically, native Android applications are only allowed to render the UI through layouts in resource directory. So, if your application needs to change the UI more often, updating the application to users becomes more time-consuming.

In other world, some applications need to update UI on the fly. Eg., your application can change the UI in a flexible way, users can choose the theme and download it from your server, update directly into the application?


## Features

Below are the completed features, unchecked items that are developed in the future.

- [x] Built-in JSON converter to convert JSON into Android layouts.
- [x] Parsers extensible, for customized other file types.
- [x] Source layouts can be stored in Assets, Raw resources, File, external storage, and InputStream.
- [x] Support tools to convert Android layout XML files into built-in JSON parser.
- [x] Extendable Widgets attributes, that help hightly support Widgets which customized by user.
- [ ] Databinding supports.
- [ ] Add listeners when layout changes.


## Additional Tools

ADL supports built-in tools (**ADL-Tools**), currently support converter to parse your layouts into JSON files. To use **ADL-Tools**, make sure your computer installed Python 2.x already.

MacOS/Linux already installed Python 2.x so you can skip installation. For Windows users, you need to download Python 2.7 from [Python official site](https://www.python.org/downloads/release/python-278/).

Download &amp; clone directory named ["adl-tools"](https://github.com/dphans/AndroidDynamicLayout/tree/master/adl-tools) from git, It's recommended to copy `adl-tools` into your project root.

**To convert layout files into JSON, open Terminal, type:**

```bash
python ./adl-tools/adl.py convert -input <input_file> -output <output_file>.json
```

*For example, I've copied `adl-tools` into Android project root, then convert `main_activity.xml` into JSON file into `assets` directory:*

```bash
python ./adl-tools/adl.py convert -input ./app/src/main/res/layout/activity_main.xml -output ./app/src/main/assets/main_activity.json
```

(See [layout file](https://github.com/dphans/AndroidDynamicLayout/blob/master/sample/src/main/res/layout/activity_main.xml) and [converted result](https://github.com/dphans/AndroidDynamicLayout/blob/master/sample/src/main/assets/layouts/main_activity.json))


## Installation

#### Gradle Dependency

**Step 1.** Add the JitPack repository to your build file (root level):

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

**Step 2.** Add the dependency (replace `{latest_version}` with latest version of library, see the version from `releases` section):

```
dependencies {
    implementation 'com.github.dphans:AndroidDynamicLayout:{latest_version}'
}
```

*If you need to use latest build pending for stable version updates, use SNAPSHOT from master branch instead:*

```
dependencies {
    implementation 'com.github.dphans:AndroidDynamicLayout:master-SNAPSHOT'
}
```


## Getting Started

#### Basic

The simplest way to update `View` into **Activities** or **Fragments** via `ADL.Builder`. The `ADL.Builder::createView` method return `View` instance, so you can set as content view if needed.

With Kotlin block, you can prepare configurations before creating `View` through middleware `ADL.Builder::build` method.

**Activity**

```kotlin
class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(ADL.Builder(context = this).build {
            this.setLayoutJson(
                assets = "layouts/main_activity.json"
            )
        })
    }

}
```

**Fragment**

```kotlin
class SampleFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ADL.Builder(this.requireContext())
            .setLayoutJson(assets = "layouts/main_activity.json")
            .createView()
    }
    
}
```

**Inside others ViewGroup**

```kotlin
val playerLayout: FrameLayout = this.findViewById(R.id.player_container)

val musicPlayerContentFile = File("/path/to/external/storate/layout/file")
val playerContents = ADL.Builder(this.requireContext())
	.setLayoutJson(file = musicPlayerContentFile)
	.createView()

playerLayout.addView(playerContents)
```

## Histories

- 2019/09/16: First initial project.


## Contributing

If you would like to contribute code you can do so through GitHub by forking the [repository](https://github.com/dphans/AndroidDynamicLayout) and sending a pull request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible.


## Contact support

Questions are welcome! Any questions or issues I will try to fix it and reply your requests by tags, before opening a new issue, please double-check that someone hasn't asked before.

For quick support, please contact me via:
Email: dinophan94@gmail.com
