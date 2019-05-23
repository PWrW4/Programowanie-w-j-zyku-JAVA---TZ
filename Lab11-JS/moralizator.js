var zakazane_slowa = ["vim", "deficyt", "c++", "zła", "java", "js"]

function process(input){
    var temp = input;
    for(i = 0; i < zakazane_slowa.length; i++){
        if(temp.indexOf(zakazane_slowa[i])>0) {
           temp = temp.replaceAll(zakazane_slowa[i], "*****");
        }
    }
    return temp;
}