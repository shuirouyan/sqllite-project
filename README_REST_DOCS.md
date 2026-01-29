# Spring REST Docs 集成说明

## 概述

本项目已集成 Spring REST Docs，用于生成 REST API 文档。文档涵盖以下 Controller：

- `CommandController`: 命令管理相关接口
- `UserController`: 用户相关接口

## 项目结构

```
src/
├── docs/
│   └── asciidoc/
│       ├── api-docs.adoc       # API文档主文件
│       └── index.adoc          # 索引文件
└── test/
    └── java/
        └── com/sqllite/sqlliteproject/controller/
            ├── CommandControllerApiDocumentation.java   # Command接口文档测试
            └── UserControllerApiDocumentation.java       # User接口文档测试
```

## 生成文档

### 1. 运行测试生成文档片段

```bash
# 运行所有文档测试
./mvnw clean test

# 或只运行特定的文档测试
./mvnw test -Dtest=CommandControllerApiDocumentation
./mvnw test -Dtest=UserControllerApiDocumentation

# Windows用户使用
mvnw.cmd test -Dtest=CommandControllerApiDocumentation,UserControllerApiDocumentation
```

测试运行后，文档片段将生成在 `target/generated-snippets/` 目录下。

### 2. 生成HTML文档

```bash
./mvnw clean package

# 或在Windows上
mvnw.cmd package -DskipTests
```

生成的HTML文档位于 `target/generated-docs/` 目录下，打开 `target/generated-docs/index.html` 即可查看完整的API文档。

### 3. 一步到位生成文档

```bash
# 运行测试并生成HTML文档
mvnw.cmd test -Dtest=CommandControllerApiDocumentation,UserControllerApiDocumentation && mvnw.cmd package -DskipTests
```

## 文档测试文件

### CommandControllerApiDocumentation

为以下接口生成文档：

1. `POST /api/command` - 创建命令
2. `GET /api/command/{id}` - 根据ID查询命令
3. `GET /api/command` - 查询所有命令
4. `GET /api/command/search` - 根据文本搜索命令
5. `PUT /api/command/{id}` - 更新命令
6. `DELETE /api/command/{id}` - 删除命令
7. `DELETE /api/command/all` - 删除所有命令
8. `GET /api/command/count` - 统计命令数量

**注意**: 使用 `@SpringBootTest` 进行集成测试，会启动完整的Spring上下文，测试会访问真实的数据库。MockMvc 在 `@BeforeEach` 方法中手动配置。

### UserControllerApiDocumentation

为以下接口生成文档：

1. `GET /api/user/test` - 测试数据库连接
2. `GET /api/user/sqllite` - SQLite连接测试
3. `GET /api/user/schema` - 获取用户表结构
4. `GET /api/user/classpath` - 获取classpath路径
5. `GET /api/user/insert` - 插入测试用户数据

**注意**: 使用 `@SpringBootTest` 进行集成测试，会启动完整的Spring上下文，测试会访问真实的数据库。MockMvc 在 `@BeforeEach` 方法中手动配置。

## Maven配置

### 依赖项

```xml
<dependency>
    <groupId>org.springframework.restdocs</groupId>
    <artifactId>spring-restdocs-mockmvc</artifactId>
    <scope>test</scope>
</dependency>
```

### Maven插件

```xml
<plugin>
    <groupId>org.asciidoctor</groupId>
    <artifactId>asciidoctor-maven-plugin</artifactId>
    <version>3.0.0</version>
    <executions>
        <execution>
            <id>generate-docs</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>process-asciidoc</goal>
            </goals>
            <configuration>
                <backend>html</backend>
                <doctype>book</doctype>
                <attributes>
                    <snippets>${project.build.directory}/generated-snippets</snippets>
                </attributes>
                <sourceDirectory>src/docs/asciidoc</sourceDirectory>
                <outputDirectory>${project.build.directory}/generated-docs</outputDirectory>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## 文档特性

1. **自动生成**: 文档基于测试自动生成，保证文档与代码同步
2. **类型安全**: 使用MockMvc进行测试，编译时检查类型
3. **易维护**: 文档作为代码的一部分，易于版本控制
4. **格式美观**: 使用AsciiDoc语法，支持生成HTML和PDF等多种格式
5. **内容丰富**: 包含请求示例、响应示例、参数说明等详细信息

## 查看文档

生成文档后，可以通过以下方式查看：

1. **本地HTML**: 直接在浏览器中打开 `target/generated-docs/index.html`
2. **AsciiDoc源码**: 查看并编辑 `src/docs/asciidoc/api-docs.adoc`

## 注意事项

1. 每次修改Controller接口时，请同步更新对应的文档测试
2. **重要**: 当前使用 `@SpringBootTest` 进行集成测试，测试会访问真实的数据库，确保数据库已正确配置
3. 如果需要使用Mock数据进行独立测试，可以将 `@SpringBootTest` 替换为 `@WebMvcTest` 并添加 `@MockBean` 注解
4. 建议在CI/CD流程中包含文档生成步骤，确保文档始终是最新的
5. ObjectMapper 由Spring自动注入到测试上下文中
