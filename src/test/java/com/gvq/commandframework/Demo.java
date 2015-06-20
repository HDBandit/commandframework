package com.gvq.commandframework;

import com.gvq.commandframework.model.impl.*;

public class Demo {

    public static void main(String[] args) {



        RecoverableCommand recoverableHomeCommand = RecoverableCommandBuilder.newBuilder()
                                                    .setCommand(
                                                                    CommandSequenceBuilder.newBuilder()
                                                                    .addCommand(ParallelCommandBuilder.newBuilder()
                                                                                    .addCommand(new HomeCommand())
                                                                                    .addCommand(new HomeCommand())
                                                                                    .build()
                                                                                )
                                                                    .addCommand(new MoveCommand())
                                                                    .build()
                                                                )
                                                    .setRetries(3)
                                                    .setErrorHandler(
                                                                        ErrorHandlingMappingBuilder.newBuilder()
                                                                        .addErrorHandler(new MyErrorHandler())
                                                                        .build()
                                                                    )
                                                    .build();




    }

}
