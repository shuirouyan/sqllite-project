package com.sqllite.sqlliteproject.service;

import com.sqllite.sqlliteproject.entity.Command;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CommandService单元测试类
 */
@SpringBootTest
class CommandServiceTest {

    @Autowired
    private CommandService commandService;

    /**
     * 每个测试方法执行后清理数据
     */
    @AfterEach
    void cleanup() {
        commandService.deleteAllCommands();
    }

    @Test
    void testCreateCommand() {
        int affectedRows = commandService.createCommand("Test Command", 100);
        assertEquals(1, affectedRows, "创建命令应返回受影响的行数为1");
    }

    @Test
    void testCreateMultipleCommands() {
        int firstAffectedRows = commandService.createCommand("First Command", 100);
        assertEquals(1, firstAffectedRows);

        int secondAffectedRows = commandService.createCommand("Second Command", 200);
        assertEquals(1, secondAffectedRows);
    }

    @Test
    void testGetCommandById() {
        commandService.createCommand("Test Command", 100);

        Command command = commandService.getCommandById(1);
        assertNotNull(command, "应能查询到ID为1的命令");
        assertEquals(1, command.getId());
        assertEquals("Test Command", command.getText());
        assertEquals(100, command.getNum());
    }

    @Test
    void testGetCommandByIdNotFound() {
        Command command = commandService.getCommandById(999);
        assertNull(command, "查询不存在的命令应返回null");
    }

    @Test
    void testGetAllCommands() {
        commandService.createCommand("First Command", 100);
        commandService.createCommand("Second Command", 200);
        commandService.createCommand("Third Command", 300);

        List<Command> commands = commandService.getAllCommands();
        assertEquals(3, commands.size(), "应能查询到3条命令");
        assertTrue(commands.get(0).getId() > commands.get(1).getId(), "命令列表应按ID降序排列");
    }

    @Test
    void testGetAllCommandsWhenEmpty() {
        List<Command> commands = commandService.getAllCommands();
        assertNotNull(commands);
        assertTrue(commands.isEmpty(), "当没有命令时应返回空列表");
    }

    @Test
    void testGetCommandsByText() {
        commandService.createCommand("Hello World", 100);
        commandService.createCommand("Hello Java", 200);
        commandService.createCommand("Python Code", 300);

        List<Command> commands = commandService.getCommandsByText("Hello");
        assertEquals(2, commands.size(), "应能搜索到包含'Hello'的2条命令");
    }

    @Test
    void testGetCommandsByTextNoMatch() {
        commandService.createCommand("Hello World", 100);

        List<Command> commands = commandService.getCommandsByText("NotExist");
        assertTrue(commands.isEmpty(), "搜索不存在的文本应返回空列表");
    }

    @Test
    void testUpdateCommand() {
        commandService.createCommand("Original Text", 100);

        int affectedRows = commandService.updateCommand(1, "Updated Text", 200);
        assertEquals(1, affectedRows, "更新命令应返回受影响的行数为1");

        Command updatedCommand = commandService.getCommandById(1);
        assertEquals("Updated Text", updatedCommand.getText());
        assertEquals(200, updatedCommand.getNum());
    }

    @Test
    void testUpdateCommandNotFound() {
        int affectedRows = commandService.updateCommand(999, "Updated Text", 200);
        assertEquals(0, affectedRows, "更新不存在的命令应返回受影响行数为0");
    }

    @Test
    void testDeleteCommand() {
        commandService.createCommand("Test Command", 100);

        int affectedRows = commandService.deleteCommand(1);
        assertEquals(1, affectedRows, "删除命令应返回受影响的行数为1");

        Command command = commandService.getCommandById(1);
        assertNull(command, "删除后应查询不到该命令");
    }

    @Test
    void testDeleteCommandNotFound() {
        int affectedRows = commandService.deleteCommand(999);
        assertEquals(0, affectedRows, "删除不存在的命令应返回受影响行数为0");
    }

    @Test
    void testDeleteAllCommands() {
        commandService.createCommand("First Command", 100);
        commandService.createCommand("Second Command", 200);
        commandService.createCommand("Third Command", 300);

        int affectedRows = commandService.deleteAllCommands();
        assertEquals(3, affectedRows, "删除所有命令应返回受影响行数为3");

        List<Command> commands = commandService.getAllCommands();
        assertTrue(commands.isEmpty(), "删除后应没有命令");
    }

    @Test
    void testDeleteAllCommandsWhenEmpty() {
        int affectedRows = commandService.deleteAllCommands();
        assertEquals(0, affectedRows, "删除空表时应返回受影响行数为0");
    }

    @Test
    void testCountCommands() {
        commandService.createCommand("First Command", 100);
        commandService.createCommand("Second Command", 200);
        commandService.createCommand("Third Command", 300);

        int count = commandService.countCommands();
        assertEquals(3, count, "统计命令数量应返回3");
    }

    @Test
    void testCountCommandsWhenEmpty() {
        int count = commandService.countCommands();
        assertEquals(0, count, "统计空表命令数量应返回0");
    }

    @Test
    void testFullCrudFlow() {
        // Create
        int createAffectedRows = commandService.createCommand("Test Command", 100);
        assertEquals(1, createAffectedRows);

        // Read
        Command command = commandService.getCommandById(1);
        assertNotNull(command);
        assertEquals("Test Command", command.getText());

        // Update
        int updateAffectedRows = commandService.updateCommand(1, "Updated Command", 200);
        assertEquals(1, updateAffectedRows);

        Command updatedCommand = commandService.getCommandById(1);
        assertEquals("Updated Command", updatedCommand.getText());
        assertEquals(200, updatedCommand.getNum());

        // Delete
        int deleteAffectedRows = commandService.deleteCommand(1);
        assertEquals(1, deleteAffectedRows);

        Command deletedCommand = commandService.getCommandById(1);
        assertNull(deletedCommand);
    }
}
