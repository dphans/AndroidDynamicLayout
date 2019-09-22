# AndroidDynamicLayout (ADL)
Flexible android layouts with high customization.

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![JitPack](https://jitpack.io/v/dphans/AndroidDynamicLayout.svg)](https://jitpack.io/#dphans/AndroidDynamicLayout/0.0.1)


## Purposes

Basically, native Android applications are only allowed to render the UI through layouts in resource directory. So, if your application needs to change the UI more often, updating the application to users becomes more time-consuming.

In other world, some applications need to update UI on the fly. Eg., your application can change the UI in a flexible way, users can choose the theme and download it from your server, update directly into the application?


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


## Documentation

For documentation and more information please visit the [website](http://dphans.github.io/pages/library/adl/).


## Contributing

If you would like to contribute code you can do so through GitHub by forking the [repository](https://github.com/dphans/AndroidDynamicLayout) and sending a pull request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible.


## Contact support

Questions are welcome! Any questions or issues I will try to fix it and reply your requests by tags, before opening a new issue, please double-check that someone hasn't asked before.

For quick support, please contact me via:
Email: dinophan94@gmail.com
