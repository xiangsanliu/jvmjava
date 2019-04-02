# jvmjava
jvm-java: A simple jvm based on Java.
Reference to [jvm-go](https://github.com/zxh0/jvmgo-book ).

### Problems And Solutions
#### Pointer and reference
For there is no pointer like C++ in Java, only reference can we use to simulate pointer. But when it comes to some instruction like dup(Duplicate the top operand stack value), if we simply duplicate the reference, problems would occur.
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
If we do something to the top element of the stack, the second top of the stack would do the same, since they refer to the same Object.
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
        // This is also called "Deep Copy".
    }
}
```