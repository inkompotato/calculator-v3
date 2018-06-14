
/**
 * UserInterface
 *
 * @author Jan Schelhaas and Larissa Wagnerberger
 * @version 2018.06.13
 */
class Engine {
    private String displayString = "";
    private boolean hexMode = false;
    private String error = "";

    Engine() {
        clear();
    }

    void numberPressed(int number) {
        displayString += number;
    }

    void equals() {
        try {
            Postfix p = new Postfix();
            //System.out.println(hexMode);
            if (!hexMode) {
                displayString = String.valueOf(p.evaluate2(p.infixToPostfix(displayString), false));
            } else {
                int result = p.evaluate2(p.infixToPostfix(displayString), true);
                String sign = (Math.signum(result) < 0) ? "-" : "";
                displayString = sign + Integer.toHexString(Math.abs(result)).toUpperCase();
            }
            error = "";

        } catch (Exception e) {
            error = "Error while parsing expression: Illegal Character";
            clear();
        }
    }

    boolean getHexMode() {
        return hexMode;
    }

    void setHexMode(boolean hexMode) {

        if (hexMode) {
            try {
                int number = Integer.parseInt(displayString);
                String sign = (Math.signum(number) < 0) ? "-" : "";
                displayString = sign + Integer.toHexString(Math.abs(number)).toUpperCase();
                error = "";
            } catch (Exception e) {
                if (!displayString.equals(""))
                    error = "Illegal Characters in DEC String";
            }
        } else {
            try {
                String sign = (displayString.matches("^-.*")) ? "-" : "";
                displayString = displayString.replaceAll("^-", "");
                int decoded = Integer.decode("0x" + displayString);
                displayString = sign + String.valueOf(decoded);
                error = "";
            } catch (Exception e) {
                if (!displayString.equals(""))
                    error = "Illegal Characters in HEX String";
            }
        }
        this.hexMode = hexMode;
    }

    void negate() {
        try {
            String sign = (displayString.matches("^-.*")) ? "" : "-";
            displayString = displayString.replaceAll("^-", "");
            int decoded;
            if (hexMode) {
                decoded = Integer.decode("0x" + displayString);
                displayString = sign + Integer.toHexString(decoded).toUpperCase();
            } else {
                decoded = Integer.parseInt(displayString);
                displayString = sign + String.valueOf(decoded);
            }
            error = "";

        } catch (Exception e) {
            error = "Unable to negate this expression";
        }
    }


    void op(String op) {
        displayString += op;
    }

    String getDisplayString() {
        return displayString;
    }

    void clear() {
        displayString = "";
    }

    String getStatus() {
        if (hexMode)
            return "HEX";
        else
            return "DEC";
    }

    String getError() {
        return error;
    }


    public String setIntersect(String set1, String set2) {
        Set<Integer> resultset = toSet(set1).intersect(toSet(set2));
        return resultset.toString();
    }

    public String setUnion(String set1, String set2) {
        return (toSet(set1).combine(toSet(set2))).toString();
    }

    public String setSubtract(String set1, String set2) {
        return (toSet(set1).subtract(toSet(set2))).toString();
    }

    public String addToSet() {

        try{
            Integer.parseInt(displayString);

            return displayString;
        } catch (Exception ignored){}
        return "";
    }

    private Set<Integer> toSet(String str) {

        Set<Integer> set = new Set<>();
        try {
            String[] strarray = str.split(",");
            for (String s : strarray) {
                set.add(Integer.parseInt(s));
            }
        } catch (Exception ignored) {

        }
        return set;
    }

    public int getSize(String str){
        return toSet(str).size();
    }


}
