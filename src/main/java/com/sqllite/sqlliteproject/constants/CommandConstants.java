package com.sqllite.sqlliteproject.constants;

/**
 * 命令相关常量类
 * 包含命令模块的SQL语句、日志消息和搜索通配符等常量
 */
public class CommandConstants {

    /**
     * SQL语句常量
     * 定义命令相关的数据库操作SQL语句
     */
    public static class SQL {
        /** 插入命令数据的SQL语句 */
        public static final String INSERT_COMMAND = "INSERT INTO command (text, num) VALUES (?, ?)";

        /** 根据ID查询命令的SQL语句 */
        public static final String SELECT_BY_ID = "SELECT id, text, num FROM command WHERE id = ?";

        /** 查询所有命令的SQL语句，按ID降序排列 */
        public static final String SELECT_ALL = "SELECT id, text, num FROM command ORDER BY id DESC";

        /** 根据文本模糊搜索命令的SQL语句 */
        public static final String SEARCH_BY_TEXT = "SELECT id, text, num FROM command WHERE text LIKE ? ORDER BY id DESC";

        /** 更新命令数据的SQL语句 */
        public static final String UPDATE_COMMAND = "UPDATE command SET text = ?, num = ? WHERE id = ?";

        /** 根据ID删除命令的SQL语句 */
        public static final String DELETE_BY_ID = "DELETE FROM command WHERE id = ?";

        /** 删除所有命令的SQL语句 */
        public static final String DELETE_ALL = "DELETE FROM command";

        /** 统计命令数量的SQL语句 */
        public static final String COUNT_ALL = "SELECT COUNT(*) FROM command";
    }

    /**
     * 日志消息常量
     * 定义命令操作相关的日志输出模板
     */
    public static class LogMessage {
        /** 命令创建成功日志，包含命令文本、数字和影响行数 */
        public static final String COMMAND_CREATED = "Created command, text: {}, num: {}, affected rows: {}";

        /** 根据ID查询命令错误日志 */
        public static final String COMMAND_QUERY_ERROR = "Error querying command by id: {}";

        /** 命令列表查询成功日志，包含查询到的命令数量 */
        public static final String COMMANDS_RETRIEVED = "Retrieved {} commands";

        /** 命令搜索结果日志，包含匹配的命令数量和搜索关键词 */
        public static final String COMMANDS_SEARCH_RESULT = "Retrieved {} commands with text containing: {}";

        /** 命令更新成功日志，包含命令ID、文本、数字和影响行数 */
        public static final String COMMAND_UPDATED = "Updated command, id: {}, text: {}, num: {}, affected rows: {}";

        /** 命令删除成功日志，包含命令ID和影响行数 */
        public static final String COMMAND_DELETED = "Deleted command, id: {}, affected rows: {}";

        /** 所有命令删除成功日志，包含影响行数 */
        public static final String ALL_COMMANDS_DELETED = "Deleted all commands, affected rows: {}";
    }

    /**
     * 搜索通配符常量
     * 用于SQL LIKE语句的模糊匹配
     */
    public static final String SEARCH_WILDCARD = "%";
}
