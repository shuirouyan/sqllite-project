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
- `UserServiceTest`: 测试服务层业务逻辑
- `UserControllerTest`: 测试控制器接口

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

## 常见问题

### Q: 数据库文件在哪里？

A: 数据库文件位于项目根目录下的 `db` 文件夹中，文件名为 `app.db`。

### Q: 如何修改数据库连接配置？

A: 修改 `src/main/resources/application.properties` 文件中的数据库相关配置。

### Q: SQLite 支持并发访问吗？

A: SQLite 支持多读单写。项目中配置了 WAL（Write-Ahead Logging）模式以提高并发性能。

## 许可证

本项目仅供学习和参考使用。

## 联系方式

如有问题或建议，请联系项目维护者。
