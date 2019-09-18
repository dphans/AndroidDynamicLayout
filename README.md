# AndroidDynamicLayout (ADL)
Flexible android layouts with high customization.


## Features

Below are the completed features, unchecked items that are developed in the future.

- [x] Built-in JSON converter to convert JSON into Android layouts.
- [x] Parsers extensible, for customized other file types.
- [x] Source layouts can be stored in Assets, Raw resources, File, external storage, and InputStream.
- [x] Support tools to convert Android layout XML files into built-in JSON parser.
- [x] Extendable Widgets attributes, that help hightly support Widgets which customized by user.
- [ ] Databinding supports.
- [ ] Add listeners when layout changes.


## Gradle Dependency

The stable version of library will be available on Jitpack soon.


## Basics

The simplest way to update View into Activities or Fragments via `ADL.Builder`:

### Activity

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

### Fragment

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

### Or... just inside layouts

```kotlin
val playerLayout: FrameLayout = this.findViewById(R.id.player_container)

val musicPlayerContentFile = File("/path/to/external/storate/layout/file")
val playerContents = ADL.Builder(this.requireContext())
	.setLayoutJson(file = musicPlayerContentFile)
	.createView()

playerLayout.addView(playerContents)
```


## Contributing

If you would like to contribute code you can do so through GitHub by forking the repository and sending a pull request.

When submitting code, please make every effort to follow existing conventions and style in order to keep the code as readable as possible.


## Contact support

Questions are welcome! Any questions or issues I will try to fix it and reply your requests by tags, before opening a new issue, please double-check that someone hasn't asked before.

For quick support, please contact me via:
Email: dinophan94@gmail.com
