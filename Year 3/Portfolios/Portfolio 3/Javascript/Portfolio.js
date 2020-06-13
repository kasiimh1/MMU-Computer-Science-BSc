class ArithmeticTaskRunner {
    constructor() {
        this.tasks = []
    }

    addNegationTask() {
        return this.tasks.push(x => {
            return -x })
    }
    addAdditionTask(arg) {
        return this.tasks.push(x => { 
            return x + arg })
    }
    addMultiplicationTask(arg) {
        return this.tasks.push(x => {
            return x * arg })
    }
    execute(startValue = 0) {
        return this.tasks.reduce((x, y) => y(x), startValue)
    }
    get taskCount() {
        return this.tasks.length
    }
}

print = console.log

 // TEST CASE 1
    print("\nTEST CASE 1")
    taskRunner =  new ArithmeticTaskRunner()
    taskRunner.addAdditionTask(10)
    taskRunner.addNegationTask()
    taskRunner.addMultiplicationTask(0.5)
    print(taskRunner.execute())
    print(taskRunner.execute(10))
    print("Task Count is: %s" ,taskRunner.taskCount)
 
 // TEST CASE 2
    print("\nTEST CASE 2")
    taskRunner =  new ArithmeticTaskRunner()
    taskRunner.addAdditionTask(2)
    taskRunner.addMultiplicationTask(4)
    taskRunner.addAdditionTask(10)
    print(taskRunner.execute(2))
    print(taskRunner.execute(-2))
    print("Task Count is: %s" ,taskRunner.taskCount)
 
 // TEST CASE 3
    print("\nTEST CASE 3")
    taskRunner =  new ArithmeticTaskRunner()
    taskRunner.addAdditionTask(2)
    taskRunner.taskCount = 5
    print("Task Count is: %s" ,taskRunner.taskCount)
