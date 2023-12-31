# build-tools

Build tools (mostly annotation processors) for LearnLib-related projects.

## Release process

The release process of this project is similar to that of [LearnLib](https://github.com/LearnLib/learnlib/wiki/Performing-a-LearnLib-Release) with the exception that this project does not need to publish its `mvn site` documentation because none of the annotations are `@Documented` and therefore will not be referenced in any third-party code.

## Acknowledgements

* The builder generator is based on [misberner/buildergen](https://github.com/misberner/buildergen), licensed under Apache-2.0.
* The EDSL generator is based on [misberner/duzzt](https://github.com/misberner/duzzt), licensed under Apache-2.0.