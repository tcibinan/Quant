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
public class TaskTimeEditCommand extends QuantCommand {

    public Queue<QuantMessage> perform(QuantMessage input, HandlingProcess handlingProcess) {
        Queue<QuantMessage> output = new LinkedList<QuantMessage>();
        TaskEntity taskEntity = handlingProcess.getParameter(HandlingProcess.TASK, TaskEntity.class);
        String answer;
        if (!isInit()) {
            answer = ctx.getMessage("command.edittask.edittime.intro", null, handlingProcess.getAccountEntity().LOCALE);
            init();
        } else {
            DateExtractor dateExtractor = new DateExtractor(input.getText());
            if (dateExtractor.isCorrect()) {
                taskEntity.extractDate(dateExtractor);
                dataService.save(taskEntity);
                answer = ctx.getMessage("command.edittask.succesfullend", null, handlingProcess.getAccountEntity().LOCALE);
                handlingProcess.clearParameters();
                handlingProcess.changeState(HandlingState.DEFAULT);
                output.add(new OutputMessage(input, answer).setKeyboard(KeyboardEnum.DEFAULT));
                return output;
            } else {
                answer = ctx.getMessage("command.edittask.edittime.toconfirm", null, handlingProcess.getAccountEntity().LOCALE);
            }
        }
        output.add(new OutputMessage(input, answer));
        return output;
    }
}
