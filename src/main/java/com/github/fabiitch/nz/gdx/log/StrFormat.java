package com.github.fabiitch.nz.gdx.log;

//TODO LOG4J org/slf4j/helpers/MessageFormatter.java
//TODO version sb private pour les games
public class StrFormat {
    static final String DELIM_STR = "{}";

    private final static StringBuilder SB = new StringBuilder(0);

    //TODO coupe les messages voir com/nzt/mfl/engine/services/sort/OrderGameService.java:18
    public static String format(String message, Object... args) {
        SB.setLength(0);
        int msgIndex = 0;
        int delimIndex;

        for (int argsCount = 0; argsCount < args.length; argsCount++) {
            delimIndex = message.indexOf(DELIM_STR, msgIndex);

            if (delimIndex == -1) {//end
                if (msgIndex == 0)
                    return message;
                else
                    SB.append(message, msgIndex, message.length());
            } else {
                SB.append(message, msgIndex, delimIndex);
                SB.append(args[argsCount]);
                msgIndex = delimIndex + 2;
            }
        }
        return SB.toString();
    }
}
