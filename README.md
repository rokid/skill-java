## Quick Start
### 要求
基于jdk1.8
1. 先安装依赖，  [rokid-skill-kit4j]
2. 运行 `SKillDemoApplication` 启动类。


## 项目功能
java skill demo，用于快速开发具有语音功能的skill。

## 项目特点
1. 使用 `skill-kit4j`，抽象出通用功能，进行统一的封装，集成基础功能。抽象之后技能开发只需要关注processor的编写，即只需关注业务，使代码更加简洁。
2. 性能的提高，在服务启动时，通过反射的形式注册路由映射，并将映射关系缓存在Map中，请求进入时无需每次通过反射来确定所有执行的方法。

## 使用说明
通过注解的形式来进行意图和事件的业务逻辑开发。
- `@Action(intent = Intent.HELLO)`表示指定该方法进行 `hello` intent的处理。其中 `Intent` 是定义在代码中的一个接口，用于存储意图的全局变量。意图名称可由开发者自行定义。

- `@Action(event = Event.VOICE_FINISHED)`表示指定该方法进行 `Voice.FINISHED` 事件的处理，其中 `Event` 为定义在 rokid-skill-kit4j 基础包中的接口，用于存储系统级事件，此部分不允许修改，请严格按照已有事件使用。

## 测试用例
使用postman进行测试

接口链接：`http://localhost:8080/skill-demo/speechlet`

测试用例在工程目录：`test/java/rokid/skill/demo/hello-intent.json`
为一个json文件，用于测试名为 hello 的 intent。


