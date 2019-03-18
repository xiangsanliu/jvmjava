package com.xiang.jvmjava.instruction.base;

import com.xiang.jvmjava.classfile.rtda.Frame;
import com.xiang.jvmjava.instruction.BytecodeReader;
import com.xiang.jvmjava.instruction.constants.Const;
import com.xiang.jvmjava.instruction.constants.IPush;
import com.xiang.jvmjava.instruction.constants.NOP;
import com.xiang.jvmjava.instruction.load.*;
import com.xiang.jvmjava.instruction.math.*;
import com.xiang.jvmjava.instruction.stack.POP;
import com.xiang.jvmjava.instruction.stack.POP2;
import com.xiang.jvmjava.instruction.stack.Swap;
import com.xiang.jvmjava.instruction.stack.dup.*;
import com.xiang.jvmjava.instruction.store.*;

/**
 * @author 项三六
 * @time 2019/3/17 17:44
 * @comment
 */

public abstract class Instruction {

    public abstract void fetchOperands(BytecodeReader reader);

    public abstract void Execute(Frame frame);

    public Instruction newInstruction(byte opcode) {
        switch (Byte.toUnsignedInt(opcode)) {
            case 0x00:
                return new NOP();
            case 0x01:
                return new Const.AConstNull();
            case 0x02:
                return new Const.IConstM1();
            case 0x03:
                return new Const.IConst0();
            case 0x04:
                return new Const.IConst1();
            case 0x05:
                return new Const.IConst2();
            case 0x06:
                return new Const.IConst3();
            case 0x07:
                return new Const.IConst4();
            case 0x08:
                return new Const.IConst5();
            case 0x09:
                return new Const.LConst0();
            case 0x0a:
                return new Const.LConst1();
            case 0x0b:
                return new Const.FConst0();
            case 0x0c:
                return new Const.FConst1();
            case 0x0d:
                return new Const.FConst2();
            case 0x0e:
                return new Const.DConst0();
            case 0x0f:
                return new Const.DConst1();
            case 0x10:
                return new IPush.BIPush();
            case 0x11:
                return new IPush.SIPush();
            // case 0x12:
            // 	return &LDC{}
            // case 0x13:
            // 	return &LDC_W{}
            // case 0x14:
            // 	return &LDC2_W{}
            case 0x15:
                return new ILoad.ILoadI();
            case 0x16:
                return new LLoad.LLoadL();
            case 0x17:
                return new FLoad.FLoadF();
            case 0x18:
                return new DLoad.DLoadD();
            case 0x19:
                return new ALoad.ALoadA();
            case 0x1a:
                return new ILoad.ILoad0();
            case 0x1b:
                return new ILoad.ILoad1();
            case 0x1c:
                return new ILoad.ILoad2();
            case 0x1d:
                return new ILoad.ILoad3();
            case 0x1e:
                return new LLoad.LLoad0();
            case 0x1f:
                return new LLoad.LLoad1();
            case 0x20:
                return new LLoad.LLoad2();
            case 0x21:
                return new LLoad.LLoad3();
            case 0x22:
                return new FLoad.FLoad0();
            case 0x23:
                return new FLoad.FLoad1();
            case 0x24:
                return new FLoad.FLoad2();
            case 0x25:
                return new FLoad.FLoad3();
            case 0x26:
                return new DLoad.DLoad0();
            case 0x27:
                return new DLoad.DLoad1();
            case 0x28:
                return new DLoad.DLoad2();
            case 0x29:
                return new DLoad.DLoad3();
            case 0x2a:
                return new ALoad.ALoad0();
            case 0x2b:
                return new ALoad.ALoad1();
            case 0x2c:
                return new ALoad.ALoad2();
            case 0x2d:
                return new ALoad.ALoad3();
            // case 0x2e:
            // 	return iaload
            // case 0x2f:
            // 	return laload
            // case 0x30:
            // 	return faload
            // case 0x31:
            // 	return daload
            // case 0x32:
            // 	return aaload
            // case 0x33:
            // 	return baload
            // case 0x34:
            // 	return caload
            // case 0x35:
            // 	return saload
            case 0x36:
                return new IStore.IStoreI();
            case 0x37:
                return new LStore.LStoreL();
            case 0x38:
                return new FStore.FStoreF();
            case 0x39:
                return new DStore.DStoreD();
            case 0x3a:
                return new AStore.AStoreA();
            case 0x3b:
                return new IStore.IStore0();
            case 0x3c:
                return new IStore.IStore1();
            case 0x3d:
                return new IStore.IStore2();
            case 0x3e:
                return new IStore.IStore3();
            case 0x3f:
                return new LStore.LStore0();
            case 0x40:
                return new LStore.LStore1();
            case 0x41:
                return new LStore.LStore2();
            case 0x42:
                return new LStore.LStore3();
            case 0x43:
                return new FStore.FStore0();
            case 0x44:
                return new FStore.FStore1();
            case 0x45:
                return new FStore.FStore2();
            case 0x46:
                return new FStore.FStore3();
            case 0x47:
                return new DStore.DStore0();
            case 0x48:
                return new DStore.DStore1();
            case 0x49:
                return new DStore.DStore2();
            case 0x4a:
                return new DStore.DStore3();
            case 0x4b:
                return new AStore.AStore0();
            case 0x4c:
                return new AStore.AStore1();
            case 0x4d:
                return new AStore.AStore2();
            case 0x4e:
                return new AStore.AStore3();
            // case 0x4f:
            // 	return iastore
            // case 0x50:
            // 	return lastore
            // case 0x51:
            // 	return fastore
            // case 0x52:
            // 	return dastore
            // case 0x53:
            // 	return aastore
            // case 0x54:
            // 	return bastore
            // case 0x55:
            // 	return castore
            // case 0x56:
            // 	return sastore
            case 0x57:
                return new POP();
            case 0x58:
                return new POP2();
            case 0x59:
                return new DUP();
            case 0x5a:
                return new DupX1();
            case 0x5b:
                return new DupX2();
            case 0x5c:
                return new DUP2();
            case 0x5d:
                return new Dup2X1();
            case 0x5e:
                return new Dup2X2();
            case 0x5f:
                return new Swap();
            case 0x60:
                return new ADD.IAdd();
            case 0x61:
                return new ADD.LAdd();
            case 0x62:
                return new ADD.FAdd();
            case 0x63:
                return new ADD.DAdd();
            case 0x64:
                return new SUB.ISub();
            case 0x65:
                return new SUB.LSub();
            case 0x66:
                return new SUB.FSub();
            case 0x67:
                return new SUB.DSub();
            case 0x68:
                return new MUL.IMul();
            case 0x69:
                return new MUL.LMul();
            case 0x6a:
                return new MUL.FMul();
            case 0x6b:
                return new MUL.DMul();
            case 0x6c:
                return new DIV.IDiv();
            case 0x6d:
                return new DIV.LDiv();
            case 0x6e:
                return new DIV.FDiv();
            case 0x6f:
                return new DIV.DDiv();
            case 0x70:
                return new REM.IRem();
            case 0x71:
                return new REM.LRem();
            case 0x72:
                return new REM.FRem();
            case 0x73:
                return new REM.DRem();
            case 0x74:
                return new NEG.INeg();
            case 0x75:
                return new NEG.LNeg();
            case 0x76:
                return new NEG.FNeg();
            case 0x77:
                return new NEG.DNeg();
            case 0x78:
                return new SH.ISHL();
            case 0x79:
                return new SH.LSHL();
            case 0x7a:
                return new SH.ISHR();
            case 0x7b:
                return new SH.LSHR();
            case 0x7c:
                return new SH.IUSHR();
            case 0x7d:
                return new SH.LUSHR();
            case 0x7e:
                return new AND.IAnd();
            case 0x7f:
                return new AND.LAnd();
            case 0x80:
                return new OR.IOR();
            case 0x81:
                return new OR.LOR();
            case 0x82:
                return new XOR.IXOR();
            case 0x83:
                return new XOR.LXOR();
            case 0x84:
                return new IInc();
            default:
                throw new UnsupportedOperationException();
        }
    }

}
