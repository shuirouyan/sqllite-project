package com.sqllite.sqlliteproject.controller;

import com.sqllite.sqlliteproject.constants.ControllerConstants;
import com.sqllite.sqlliteproject.constants.ResponseConstants;
import com.sqllite.sqlliteproject.dto.CommandCreateRequest;
import com.sqllite.sqlliteproject.dto.CommandUpdateRequest;
import com.sqllite.sqlliteproject.entity.Command;
import com.sqllite.sqlliteproject.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 命令控制器
 * 提供command表相关的REST API接口
 */
@RestController
@RequestMapping(ControllerConstants.CommandPath.BASE)
public class CommandController {

    @Autowired
    private CommandService commandService;

    /**
     * 创建新的命令记录
     *
     * @param request 命令创建请求
     * @return 操作结果
     */
    @PostMapping
    public Map<String, Object> createCommand(@RequestBody CommandCreateRequest request) {
        int affectedRows = commandService.createCommand(request.getText(), request.getNum());
        return Map.of(
                ResponseConstants.Field.SUCCESS, affectedRows > 0,
                ResponseConstants.Field.AFFECTED_ROWS, affectedRows
        );
    }

    /**
     * 根据ID查询命令
     *
     * @param id 命令ID
     * @return 命令对象
     */
    @GetMapping(ControllerConstants.CommandPath.ID_PATH)
    public Command getCommandById(@PathVariable Integer id) {
        return commandService.getCommandById(id);
    }

    /**
     * 查询所有命令
     *
     * @return 命令列表
     */
    @GetMapping
    public List<Command> getAllCommands() {
        return commandService.getAllCommands();
    }

    /**
     * 根据text查询命令
     *
     * @param text 搜索文本
     * @return 命令列表
     */
    @GetMapping(ControllerConstants.CommandPath.SEARCH)
    public List<Command> searchCommandsByText(@RequestParam String text) {
        return commandService.getCommandsByText(text);
    }

    /**
     * 更新命令记录
     *
     * @param id 命令ID
     * @param request 命令更新请求
     * @return 操作结果
     */
    @PutMapping(ControllerConstants.CommandPath.ID_PATH)
    public Map<String, Object> updateCommand(@PathVariable Integer id, @RequestBody CommandUpdateRequest request) {
        int affectedRows = commandService.updateCommand(id, request.getText(), request.getNum());
        return Map.of(
                ResponseConstants.Field.SUCCESS, affectedRows > 0,
                ResponseConstants.Field.AFFECTED_ROWS, affectedRows
        );
    }

    /**
     * 根据ID删除命令
     *
     * @param id 命令ID
     * @return 操作结果
     */
    @DeleteMapping(ControllerConstants.CommandPath.ID_PATH)
    public Map<String, Object> deleteCommand(@PathVariable Integer id) {
        int affectedRows = commandService.deleteCommand(id);
        return Map.of(
                ResponseConstants.Field.SUCCESS, affectedRows > 0,
                ResponseConstants.Field.AFFECTED_ROWS, affectedRows
        );
    }

    /**
     * 删除所有命令
     *
     * @return 操作结果
     */
    @DeleteMapping(ControllerConstants.CommandPath.ALL)
    public Map<String, Object> deleteAllCommands() {
        int affectedRows = commandService.deleteAllCommands();
        return Map.of(
                ResponseConstants.Field.SUCCESS, true,
                ResponseConstants.Field.AFFECTED_ROWS, affectedRows
        );
    }

    /**
     * 统计命令数量
     *
     * @return 命令总数
     */
    @GetMapping(ControllerConstants.CommandPath.COUNT)
    public Map<String, Integer> countCommands() {
        int count = commandService.countCommands();
        return Map.of(ResponseConstants.Field.COUNT, count);
    }
}
