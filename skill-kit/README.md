## rokid-skill-kit
java 工具包用于快速开发语音功能的skill


### Quick Start

```
➜ tree
.
├── README.md
├── pom.xml
├── skill-kit.iml
└── src
    └── main
        ├── java
        │   └── com
        │       └── rokid
        │           └── skill
        │               └── kit
        │                   ├── SkillContextConfig.java
        │                   ├── annotation
        │                   │   ├── Action.java
        │                   │   └── Processor.java
        │                   ├── bean
        │                   │   ├── ReqBasicInfo.java
        │                   │   ├── ServiceLog.java
        │                   │   ├── SpeechletLog.java
        │                   │   └── UserRecord.java
        │                   ├── constants
        │                   │   ├── Const.java
        │                   │   ├── InternalEvent.java
        │                   │   ├── InternalIntent.java
        │                   │   └── ServiceStatus.java
        │                   ├── controller
        │                   │   ├── HealthCheckController.java
        │                   │   └── SkillController.java
        │                   ├── core
        │                   │   ├── ActionRegistry.java
        │                   │   ├── ExtraInfo.java
        │                   │   ├── Handler.java
        │                   │   ├── PreInterceptor.java
        │                   │   ├── SkillDispatcher.java
        │                   │   ├── SkillInterceptor.java
        │                   │   ├── SkillInterceptorChain.java
        │                   │   └── SpeechletDispatcher.java
        │                   ├── exception
        │                   │   ├── ExceptionDispatcher.java
        │                   │   ├── SkillCode.java
        │                   │   ├── SkillException.java
        │                   │   ├── SkillKitCode.java
        │                   │   └── SkillKitException.java
        │                   ├── service
        │                   │   ├── HealthCheckService.java
        │                   │   ├── LogRecorderService.java
        │                   │   ├── UserRecordService.java
        │                   │   └── impl
        │                   │       ├── DefaultHealthCheckServiceImpl.java
        │                   │       ├── DefaultLogRecorderServiceImpl.java
        │                   │       └── DefaultUserRecordServiceImpl.java
        │                   └── util
        │                       ├── ActionUtil.java
        │                       ├── JacksonUtil.java
        │                       ├── LogRecorderUtil.java
        │                       ├── ProtocolUtil.java
        │                       ├── RequestUtil.java
        │                       └── ResponseUtil.java
        └── resources
            └── META-INF
                └── spring.factories

18 directories, 42 files
```

Running
-------
假设已经安装Java 8 和 Maven 3，安装 jar 包。
```
mvn install
```

## 简要说明

使用 `skill-kit`，抽象出通用功能，进行统一的封装，集成基础功能。抽象之后技能开发只需要关注 `processor` 的编写，即只需关注业务，使代码更加简洁。

#### 根本机制：Java 反射
性能的提高，在服务启动时，通过反射的形式注册路由映射，并将映射关系缓存在Map中，请求进入时无需每次通过反射来确定所有执行的方法。

### 主要功能

1. 通过注解 @Processor @Action 方式定义意图的业务逻辑
2. 统一的日志打印方式，ServiceLog, SpeechletLog
3. 统一的用户记录方式 UserRecord
4. 统一的健康检查服务 HealthCheckService
5. 内置的意图及事件，InternalIntent, InternalEvent
6. 意图的注册，本地缓存，统一路由分发机制
7. 统一的异常码定义，统一的技能异常定义
8. 统一的异常处理定义
9. 可定制化的拦截机制，如进行 intent，slot 改写等
10. 意图获取及响应构建工具


## 使用说明
通过注解的形式来进行意图和事件的业务逻辑开发。
- `@Action(intent = Intent.HELLO)`表示指定该方法进行 `hello` intent的处理。其中 `Intent` 是定义在代码中的一个接口，用于存储意图的全局变量。意图名称可由开发者自行定义。

- `@Action(event = Event.VOICE_FINISHED)`表示指定该方法进行 `Voice.FINISHED` 事件的处理，其中 `Event` 为定义在 InternalEvent中，用于存储系统级事件，此部分不允许修改，请严格按照已有事件使用。
