function process(input){
    var temp = input;
    temp = temp.replaceAll("\u017C", "rz");
    temp = temp.replaceAll("\u00F3", "u");
    temp = temp.replaceAll("e", "3");
    temp = temp.replaceAll("o", "0");
    temp = temp.replaceAll("b", "8");
    
    return temp + " xD";
}