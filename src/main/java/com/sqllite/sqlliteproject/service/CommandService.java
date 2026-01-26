package com.sqllite.sqlliteproject.service;

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
        String sql = "INSERT INTO command (text, num) VALUES (?, ?)";
        int affectedRows = jdbcTemplate.update(sql, text, num);
        logger.info("Created command, text: {}, num: {}, affected rows: {}", text, num, affectedRows);
        return affectedRows;
    }

    /**
     * 根据ID查询命令
     *
     * @param id 命令ID
     * @return 命令对象，如果不存在返回null
     */
    public Command getCommandById(Integer id) {
        String sql = "SELECT id, text, num FROM command WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new CommandRowMapper(), id);
        } catch (Exception e) {
            logger.error("Error querying command by id: {}", id, e);
            return null;
        }
    }

    /**
     * 查询所有命令
     *
     * @return 命令列表
     */
    public List<Command> getAllCommands() {
        String sql = "SELECT id, text, num FROM command ORDER BY id DESC";
        List<Command> commands = jdbcTemplate.query(sql, new CommandRowMapper());
        logger.info("Retrieved {} commands", commands.size());
        return commands;
    }

    /**
     * 根据text查询命令
     *
     * @param text 命令文本
     * @return 命令列表
     */
    public List<Command> getCommandsByText(String text) {
        String sql = "SELECT id, text, num FROM command WHERE text LIKE ? ORDER BY id DESC";
        String searchTerm = "%" + text + "%";
        List<Command> commands = jdbcTemplate.query(sql, new CommandRowMapper(), searchTerm);
        logger.info("Retrieved {} commands with text containing: {}", commands.size(), text);
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
        String sql = "UPDATE command SET text = ?, num = ? WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, text, num, id);
        logger.info("Updated command, id: {}, text: {}, num: {}, affected rows: {}", id, text, num, affectedRows);
        return affectedRows;
    }

    /**
     * 根据ID删除命令
     *
     * @param id 命令ID
     * @return 受影响的行数（1表示成功，0表示记录不存在）
     */
    public int deleteCommand(Integer id) {
        String sql = "DELETE FROM command WHERE id = ?";
        int affectedRows = jdbcTemplate.update(sql, id);
        logger.info("Deleted command, id: {}, affected rows: {}", id, affectedRows);
        return affectedRows;
    }

    /**
     * 删除所有命令
     *
     * @return 受影响的行数
     */
    public int deleteAllCommands() {
        String sql = "DELETE FROM command";
        int affectedRows = jdbcTemplate.update(sql);
        logger.info("Deleted all commands, affected rows: {}", affectedRows);
        return affectedRows;
    }

    /**
     * 统计命令数量
     *
     * @return 命令总数
     */
    public int countCommands() {
        String sql = "SELECT COUNT(*) FROM command";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
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
