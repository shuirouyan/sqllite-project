package com.sqllite.sqlliteproject.service;

import com.sqllite.sqlliteproject.constants.CommandConstants;
import com.sqllite.sqlliteproject.entity.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 命令服务类
 * 提供command表相关的CRUD操作
 */
@Service
public class CommandService {
    private Logger logger = LoggerFactory.getLogger(CommandService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建新的命令记录
     *
     * @param text 命令文本
     * @param num 数字值
     * @return 受影响的行数（1表示成功）
     */
    public int createCommand(String text, Integer num) {
        int affectedRows = jdbcTemplate.update(CommandConstants.SQL.INSERT_COMMAND, text, num);
        logger.info(CommandConstants.LogMessage.COMMAND_CREATED, text, num, affectedRows);
        return affectedRows;
    }

    /**
     * 根据ID查询命令
     *
     * @param id 命令ID
     * @return 命令对象，如果不存在返回null
     */
    public Command getCommandById(Integer id) {
        try {
            return jdbcTemplate.queryForObject(CommandConstants.SQL.SELECT_BY_ID, new CommandRowMapper(), id);
        } catch (Exception e) {
            logger.error(CommandConstants.LogMessage.COMMAND_QUERY_ERROR, id, e);
            return null;
        }
    }

    /**
     * 查询所有命令
     *
     * @return 命令列表
     */
    public List<Command> getAllCommands() {
        List<Command> commands = jdbcTemplate.query(CommandConstants.SQL.SELECT_ALL, new CommandRowMapper());
        logger.info(CommandConstants.LogMessage.COMMANDS_RETRIEVED, commands.size());
        return commands;
    }

    /**
     * 根据text查询命令
     *
     * @param text 命令文本
     * @return 命令列表
     */
    public List<Command> getCommandsByText(String text) {
        String searchTerm = CommandConstants.SEARCH_WILDCARD + text + CommandConstants.SEARCH_WILDCARD;
        List<Command> commands = jdbcTemplate.query(
                CommandConstants.SQL.SEARCH_BY_TEXT,
                new CommandRowMapper(),
                searchTerm
        );
        logger.info(CommandConstants.LogMessage.COMMANDS_SEARCH_RESULT, commands.size(), text);
        return commands;
    }

    /**
     * 更新命令记录
     *
     * @param id 命令ID
     * @param text 新的命令文本
     * @param num 新的数字值
     * @return 受影响的行数（1表示成功，0表示记录不存在）
     */
    public int updateCommand(Integer id, String text, Integer num) {
        int affectedRows = jdbcTemplate.update(CommandConstants.SQL.UPDATE_COMMAND, text, num, id);
        logger.info(CommandConstants.LogMessage.COMMAND_UPDATED, id, text, num, affectedRows);
        return affectedRows;
    }

    /**
     * 根据ID删除命令
     *
     * @param id 命令ID
     * @return 受影响的行数（1表示成功，0表示记录不存在）
     */
    public int deleteCommand(Integer id) {
        int affectedRows = jdbcTemplate.update(CommandConstants.SQL.DELETE_BY_ID, id);
        logger.info(CommandConstants.LogMessage.COMMAND_DELETED, id, affectedRows);
        return affectedRows;
    }

    /**
     * 删除所有命令
     *
     * @return 受影响的行数
     */
    public int deleteAllCommands() {
        int affectedRows = jdbcTemplate.update(CommandConstants.SQL.DELETE_ALL);
        logger.info(CommandConstants.LogMessage.ALL_COMMANDS_DELETED, affectedRows);
        return affectedRows;
    }

    /**
     * 统计命令数量
     *
     * @return 命令总数
     */
    public int countCommands() {
        Integer count = jdbcTemplate.queryForObject(CommandConstants.SQL.COUNT_ALL, Integer.class);
        return count != null ? count : 0;
    }

    /**
     * Command表行映射器
     */
    private static class CommandRowMapper implements RowMapper<Command> {
        @Override
        public Command mapRow(ResultSet rs, int rowNum) throws SQLException {
            Command command = new Command();
            command.setId(rs.getInt("id"));
            command.setText(rs.getString("text"));
            command.setNum(rs.getInt("num"));
            return command;
        }
    }
}
