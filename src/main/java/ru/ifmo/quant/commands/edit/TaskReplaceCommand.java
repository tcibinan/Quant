package ru.ifmo.quant.commands.edit;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.ifmo.quant.*;
import ru.ifmo.quant.commands.QuantCommand;
import ru.ifmo.quant.entities.TaskEntity;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by andrey on 07.12.2016.
 */
@Component
@Scope("prototype")
public class TaskReplaceCommand extends QuantCommand {

    public Queue<QuantMessage> perform(QuantMessage input, HandlingProcess handlingProcess) {
        Queue<QuantMessage> output = new LinkedList<QuantMessage>();
        TaskEntity taskEntity = handlingProcess.getParameter(HandlingProcess.TASK, TaskEntity.class);
        String answer = null;

        if (!isInit()) {
            answer = ctx.getMessage("command.edittask.replacetask.intro", null, input.getLocale());
            init();
        } else {
            DateExtractor dateExtractor = new DateExtractor(input.getText());
            TaskEntity replaceTaskEntity = new TaskEntity();
            replaceTaskEntity.setId(taskEntity.getId());
            replaceTaskEntity.setAccount(handlingProcess.getAccountEntity());
            replaceTaskEntity.setBody(dateExtractor.getText());
            if (dateExtractor.isCorrect()) {
                replaceTaskEntity.extractDate(dateExtractor);
                dataService.save(replaceTaskEntity);
                answer = ctx.getMessage("command.edittask.succesfullend", null, input.getLocale());
                handlingProcess.clearParameters();
                handlingProcess.changeState(HandlingState.DEFAULT);
            } else {
                answer = ctx.getMessage("command.edittask.replacetask", new Object[]{taskEntity.getBody()}, input.getLocale());
            }
        }
        output.add(new OutputMessage(input, answer));
        return output;
    }

}
