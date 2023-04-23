//function to clear the result screen
function clearScreen() {
    document.getElementById("result").value = "";
}

//function to display the value inputted to result
function display(value) {
    document.getElementById("result").value += value;
}

//function to take the string from result to evaluate it.
function calculate() {
    let math = document.getElementById("result").value;
    /*
    Eval() is very dangerous and should not be used under any circumstances, use Function() instead.
    Function() create a temp function called anonymous that execute the command "return math" and then we add ()
    after it to execute that anonymous function.
    */
    let result = Function("return " + math)();
    document.getElementById("result").value = result;
    if (result === Infinity){
        document.getElementById("result").value = "∞"
    }
}

