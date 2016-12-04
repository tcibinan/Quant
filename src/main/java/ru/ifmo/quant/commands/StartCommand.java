package ru.ifmo.quant.commands;

import ru.ifmo.quant.HandlingProcess;
import ru.ifmo.quant.QuantMessage;
import ru.ifmo.quant.dao.DataService;
import ru.ifmo.quant.entity.AccountEntity;
import ru.ifmo.quant.exceptions.WrongContextCommandException;

/**
 * Created by andrey on 09.11.2016.
 */
public class StartCommand extends QuantCommand {

    public String perform(QuantMessage input, HandlingProcess process) {
        StringBuilder stringBuilder;
        stringBuilder = new StringBuilder();
        if (process.getAccountEntity() == null) {
            stringBuilder.append(ctx.getMessage("command.start", null, input.getLocale())).append("\n");
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.insertKey(input.getMessageAddress());
            dataService.save(accountEntity);
        } else {
            stringBuilder.append(ctx.getMessage("command.start.again", null, input.getLocale()));
        }
        return stringBuilder.toString();
    }
}
