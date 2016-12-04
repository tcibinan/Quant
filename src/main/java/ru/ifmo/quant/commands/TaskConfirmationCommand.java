package ru.ifmo.quant.commands;

import ru.ifmo.quant.DateExtractor;
import ru.ifmo.quant.HandlingProcess;
import ru.ifmo.quant.QuantMessage;
import ru.ifmo.quant.dao.DataService;
import ru.ifmo.quant.entity.AccountEntity;
import ru.ifmo.quant.entity.TaskEntity;

/**
 * Created by andrey on 25.11.2016.
 */
public class TaskConfirmationCommand extends TaskCreatingCommand {

    public String perform(QuantMessage input, HandlingProcess process) {
        String rawText = input.getText();
        DateExtractor dateExtractor = new DateExtractor(rawText);
        TaskEntity taskEntity = process.getParameter("new-task", TaskEntity.class);
        String answer = controlFlow(input, dateExtractor, taskEntity, process, dataService);
        return answer;
    }
}
