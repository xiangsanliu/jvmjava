# jvmjava
> jvm-java: A simple jvm based on Java. As well as a java version of [jvm-go](https://github.com/zxh0/jvmgo-book ).
> Some native method implementation refer to [lxyscls/jvmjava](https://github.com/lxyscls/jvmjava)


### Problems And Solutions
#### Pointer and reference
#### Deep copy or Shallow copy
Parameter passing in Java always means reference passing (except for the primary types such as int, short ...). But attention must be paid when facing situations that need value passing.
<br/>
e.g.
<br/>
To implement instruction like dup(Duplicate the top operand stack value), if we simply duplicate the reference, problems would occur.
```java
public class DUP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
```
If we do something to the top element of the stack, the second top element of the stack would do the same, since they refer to the same Object.
<br>
e.g.
```java
public class Test {
    public void test() {
        Slot slot1 = stack.pop();
        slot1.setRef(null);
        Slot slot2 = stack.pop();
        slot2.getRef().doSomething();           // throw NullPointerException.
    }
}
```
So the following code would be right:
```java
public class DUP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(new Slot(slot));  
        // In function new Slot(slot) we generate a new object and duplicate all member from old object to the new one. 
        // This is also called "Shallow Copy".
    }
}
```