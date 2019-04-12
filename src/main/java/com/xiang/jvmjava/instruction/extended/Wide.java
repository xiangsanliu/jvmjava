package com.xiang.jvmjava.instruction.extended;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.instruction.load.*;
import com.xiang.jvmjava.instruction.math.IInc;
import com.xiang.jvmjava.instruction.store.*;

import java.io.IOException;

/**
 * @author 项三六
 * @time 2019/3/22 15:55
 * @comment
 */

public class Wide extends Instruction {

    private Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        int opcode = Byte.toUnsignedInt(reader.readInt8());
        switch (opcode) {
            case 0x15:
                modifiedInstruction = new ILoad.ILoadI().setIndex(reader.readUint16());
                return;
            case 0x16:
                modifiedInstruction = new LLoad.LLoadL().setIndex(reader.readUint16());
                return;
            case 0x17:
                modifiedInstruction = new FLoad.FLoadF().setIndex(reader.readUint16());
                return;
            case 0x18:
                modifiedInstruction = new DLoad.DLoadD().setIndex(reader.readUint16());
                return;
            case 0x19:
                modifiedInstruction = new ALoad.ALoadA().setIndex(reader.readUint16());
                return;
            case 0x36:
                modifiedInstruction = new IStore.IStoreI().setIndex(reader.readUint16());
                return;
            case 0x37:
                modifiedInstruction = new LStore.LStoreL().setIndex(reader.readUint16());
                return;
            case 0x38:
                modifiedInstruction = new FStore.FStoreF().setIndex(reader.readUint16());
                return;
            case 0x39:
                modifiedInstruction = new DStore.DStoreD().setIndex(reader.readUint16());
                return;
            case 0x3a:
                modifiedInstruction = new AStore.AStoreA().setIndex(reader.readUint16());
                return;
            case 0x84:
                modifiedInstruction = new IInc().setIndex(reader.readUint16());
                return;
            case 0xa9:
                throw new UnsupportedOperationException("0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
