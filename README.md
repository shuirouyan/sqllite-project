# SQLite Project

基于 Spring Boot 和 SQLite 的轻量级数据库应用项目。

## 项目简介

本项目是一个使用 Spring Boot 框架和 SQLite 数据库的示例项目，展示了如何在实际应用中使用嵌入式数据库进行开发。

## 技术栈

- **Java**: 17
- **Spring Boot**: 4.0.2
- **数据库**: SQLite 3.50.3.0
- **构建工具**: Maven

## 项目结构

```
sqllite-project/
├── src/
│   ├── main/
│   │   ├── java/com/sqllite/sqlliteproject/
│   │   │   ├── config/              # 配置类
│   │   │   │   ├── ApplicationAutoRunner.java
│   │   │   │   └── CommandLineAutoConfig.java
│   │   │   ├── controller/          # 控制器层
│   │   │   │   ├── UserController.java
│   │   │   │   └── CommandController.java
│   │   │   ├── service/             # 服务层
│   │   │   │   ├── UserService.java
│   │   │   │   └── CommandService.java
│   │   │   ├── entity/              # 实体类
│   │   │   │   └── Command.java
│   │   │   ├── dto/                 # 数据传输对象
│   │   │   │   ├── CommandCreateRequest.java
│   │   │   │   └── CommandUpdateRequest.java
│   │   │   ├── constants/           # 常量类
│   │   │   │   ├── UserConstants.java
│   │   │   │   ├── CommandConstants.java
│   │   │   │   ├── ResponseConstants.java
│   │   │   │   └── ControllerConstants.java
│   │   │   └── SqlliteProjectApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # 测试代码
├── db/                              # SQLite数据库文件
├── pom.xml
└── README.md
```

## 功能特性

### 数据库自动初始化
- 应用启动时自动创建 `users` 表
- 应用启动时自动创建 `command` 表

### REST API 接口

#### 用户相关接口

| 接口路径 | 方法 | 描述 |
|---------|------|------|
| `/api/users/test` | GET | 测试数据库连接 |
| `/api/users/sqllite` | GET | SQLite连接测试 |
| `/api/users/schema` | GET | 获取users表结构 |
| `/api/users/classpath` | GET | 获取classpath路径 |
| `/api/users/insert` | GET | 插入测试用户数据 |

#### 命令相关接口

| 接口路径 | 方法 | 描述 | 请求体 |
|---------|------|------|--------|
| `/api/commands` | POST | 创建命令 | `{"text": "xxx", "num": 123}` |
| `/api/commands/{id}` | GET | 根据ID查询命令 | - |
| `/api/commands` | GET | 查询所有命令 | - |
| `/api/commands/search?text=xxx` | GET | 根据文本搜索命令 | - |
| `/api/commands/{id}` | PUT | 更新命令 | `{"text": "xxx", "num": 123}` |
| `/api/commands/{id}` | DELETE | 根据ID删除命令 | - |
| `/api/commands/all` | DELETE | 删除所有命令 | - |
| `/api/commands/count` | GET | 统计命令数量 | - |

## 快速开始

### 环境要求

- JDK 17 或更高版本
- Maven 3.6 或更高版本

### 安装与运行

1. 克隆项目
```bash
git clone <repository-url>
cd sqllite-project
```

2. 编译项目
```bash
./mvnw clean install
```

3. 运行应用
```bash
./mvnw spring-boot:run
```

或者在 Windows 上：
```cmd
mvnw.cmd spring-boot:run
```

应用启动后，默认端口为 `8000`。

### 访问接口

在浏览器或使用 Postman 等工具访问以下接口：

#### 用户相关接口

```bash
# 测试数据库连接
curl http://localhost:8000/api/users/test

# 测试SQLite连接
curl http://localhost:8000/api/users/sqllite

# 获取表结构
curl http://localhost:8000/api/users/schema

# 插入测试数据
curl http://localhost:8000/api/users/insert
```

#### 命令相关接口

```bash
# 创建命令
curl -X POST http://localhost:8000/api/commands \
  -H "Content-Type: application/json" \
  -d '{"text": "test command", "num": 100}'

# 查询所有命令
curl http://localhost:8000/api/commands

# 根据ID查询命令
curl http://localhost:8000/api/commands/1

# 搜索命令
curl http://localhost:8000/api/commands/search?text=test

# 更新命令
curl -X PUT http://localhost:8000/api/commands/1 \
  -H "Content-Type: application/json" \
  -d '{"text": "updated command", "num": 200}'

# 删除命令
curl -X DELETE http://localhost:8000/api/commands/1

# 统计命令数量
curl http://localhost:8000/api/commands/count
```

## 配置说明

### 数据库配置

在 `src/main/resources/application.properties` 中配置数据库连接：

```properties
# 应用配置
spring.application.name=sqllite-project
server.port=8000

# 数据源配置
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.url=jdbc:sqlite:./db/app.db
spring.datasource.username=root
spring.datasource.password=root

# Hikari连接池配置
spring.datasource.hikari.maximum-pool-size=1
spring.datasource.hikari.connection-init-sql=PRAGMA journal_mode=WAL;
```

## 运行测试

执行单元测试：

```bash
./mvnw test
```

测试包括：
- `UserServiceTest`: 测试用户服务层业务逻辑
- `UserControllerTest`: 测试用户控制器接口
- `CommandServiceTest`: 测试命令服务层CRUD操作
- `CommandControllerTest`: 测试命令控制器接口

## 数据库表结构

### users 表
```sql
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    age INTEGER
)
```

### command 表
```sql
CREATE TABLE IF NOT EXISTS command (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    `text` TEXT NOT NULL,
    num INTEGER
)
```

**字段说明：**
- `id`: 命令ID，主键，自增
- `text`: 命令文本，必填
- `num`: 数字值，可选

## 开发说明

### 项目架构

项目采用经典的三层架构：
- **Controller层**: 处理HTTP请求和响应
- **Service层**: 实现业务逻辑
- **Entity层**: 数据库实体映射
- **DTO层**: 数据传输对象，用于接收请求参数

### 添加新的Controller

1. 在 `controller` 包下创建新的控制器类
2. 使用 `@RestController` 和 `@RequestMapping` 注解
3. 注入对应的 Service
4. 定义接口方法并添加相应注解
5. 使用专门的DTO类接收请求参数

### 添加新的Service

1. 在 `service` 包下创建新的服务类
2. 使用 `@Service` 注解
3. 注入 `JdbcTemplate`
4. 实现业务逻辑方法
5. 使用 `RowMapper` 进行结果集映射

### 添加新的Entity

1. 在 `entity` 包下创建实体类
2. 定义字段和getter/setter方法
3. 在Service中使用 `RowMapper` 进行映射

### 添加新的DTO

1. 在 `dto` 包下创建请求/响应DTO
2. 使用注解进行参数验证（如@NotNull, @Size等）
3. 在Controller中使用 `@RequestBody` 接收JSON参数

### 添加新的常量类

1. 在 `constants` 包下创建常量类
2. 按模块分组，使用内部类组织常量
3. 为每个常量添加详细的JavaDoc注释
4. 避免在代码中使用魔法字符串，统一使用常量

**常量类示例：**
- `UserConstants`: 用户相关常量（SQL、日志消息、测试数据）
- `CommandConstants`: 命令相关常量（SQL、日志消息、搜索通配符）
- `ResponseConstants`: 响应相关常量（字段名、路径前缀）
- `ControllerConstants`: 控制器相关常量（API路径、返回消息）

## 代码规范

### 常量使用

项目中所有字符串常量都定义在 `constants` 包下，避免使用魔法字符串：

- **SQL语句**：定义在对应模块的 `SQL` 内部类中
- **日志消息**：定义在对应模块的 `LogMessage` 内部类中
- **API路径**：定义在 `ControllerConstants` 中
- **响应字段**：定义在 `ResponseConstants.Field` 中

### 注释规范

- 所有类必须添加类级JavaDoc注释，说明类的用途
- 所有公共方法必须添加JavaDoc注释，说明参数和返回值
- 所有常量必须添加行内注释，说明常量的用途
- 复杂的业务逻辑需要添加必要的代码注释

### 分层架构

项目遵循严格的分层架构，各层职责明确：

- **Controller层**：只处理HTTP请求和响应，不包含业务逻辑
- **Service层**：包含所有业务逻辑，使用JdbcTemplate操作数据库
- **Entity层**：纯粹的POJO类，对应数据库表结构
- **DTO层**：用于接收和返回数据，与Entity解耦
- **Constants层**：统一管理所有常量，便于维护

### Q: 数据库文件在哪里？

A: 数据库文件位于项目根目录下的 `db` 文件夹中，文件名为 `app.db`。

### Q: 如何修改数据库连接配置？

A: 修改 `src/main/resources/application.properties` 文件中的数据库相关配置。

### Q: SQLite 支持并发访问吗？

A: SQLite 支持多读单写。项目中配置了 WAL（Write-Ahead Logging）模式以提高并发性能。

### Q: 为什么要使用常量类？

A: 使用常量类有以下优点：
- 避免魔法字符串，提高代码可读性
- 集中管理常量，便于统一修改
- 减少拼写错误的风险
- 便于代码重构和维护

### Q: 项目使用什么ORM框架？

A: 项目使用 Spring 的 `JdbcTemplate` 进行数据库操作，这是轻量级的ORM方案，适合小型项目使用。

## 许可证

本项目仅供学习和参考使用。

## 联系方式

如有问题或建议，请联系项目维护者。
