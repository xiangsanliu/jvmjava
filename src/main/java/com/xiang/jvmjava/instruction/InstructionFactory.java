package com.xiang.jvmjava.instruction;

import com.xiang.jvmjava.instruction.base.Instruction;
import com.xiang.jvmjava.instruction.cmp.*;
import com.xiang.jvmjava.instruction.constants.*;
import com.xiang.jvmjava.instruction.control.Goto;
import com.xiang.jvmjava.instruction.control.LookupSwitch;
import com.xiang.jvmjava.instruction.control.TableSwitch;
import com.xiang.jvmjava.instruction.control.retrun.*;
import com.xiang.jvmjava.instruction.conversions.D2X;
import com.xiang.jvmjava.instruction.conversions.F2X;
import com.xiang.jvmjava.instruction.conversions.I2X;
import com.xiang.jvmjava.instruction.conversions.L2X;
import com.xiang.jvmjava.instruction.extended.GotoW;
import com.xiang.jvmjava.instruction.extended.IfNotNull;
import com.xiang.jvmjava.instruction.extended.IfNull;
import com.xiang.jvmjava.instruction.extended.Wide;
import com.xiang.jvmjava.instruction.load.*;
import com.xiang.jvmjava.instruction.math.*;
import com.xiang.jvmjava.instruction.reference.*;
import com.xiang.jvmjava.instruction.reserved.InvokeNative;
import com.xiang.jvmjava.instruction.stack.POP;
import com.xiang.jvmjava.instruction.stack.POP2;
import com.xiang.jvmjava.instruction.stack.Swap;
import com.xiang.jvmjava.instruction.stack.dup.*;
import com.xiang.jvmjava.instruction.store.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 项三六
 * @time 2019/4/2 9:42
 * @comment
 */

class InstructionFactory {

    private static Map<Integer, Instruction> instructionMap = new HashMap<>();

    static {
        instructionMap.put(0x00, new NOP());
        instructionMap.put(0x01, new Const.AConstNull());
        instructionMap.put(0x02, new Const.IConstM1());
        instructionMap.put(0x03, new Const.IConst0());
        instructionMap.put(0x04, new Const.IConst1());
        instructionMap.put(0x05, new Const.IConst2());
        instructionMap.put(0x06, new Const.IConst3());
        instructionMap.put(0x07, new Const.IConst4());
        instructionMap.put(0x08, new Const.IConst5());
        instructionMap.put(0x09, new Const.LConst0());
        instructionMap.put(0x0a, new Const.LConst1());
        instructionMap.put(0x0b, new Const.FConst0());
        instructionMap.put(0x0c, new Const.FConst1());
        instructionMap.put(0x0d, new Const.FConst2());
        instructionMap.put(0x0e, new Const.DConst0());
        instructionMap.put(0x0f, new Const.DConst1());
        instructionMap.put(0x10, new IPush.BIPush());
        instructionMap.put(0x11, new IPush.SIPush());
        instructionMap.put(0x12, new LDC());
        instructionMap.put(0x13, new LDCW());
        instructionMap.put(0x14, new LDC2W());
        instructionMap.put(0x15, new ILoad.ILoadI());
        instructionMap.put(0x16, new LLoad.LLoadL());
        instructionMap.put(0x17, new FLoad.FLoadF());
        instructionMap.put(0x18, new DLoad.DLoadD());
        instructionMap.put(0x19, new ALoad.ALoadA());
        instructionMap.put(0x1a, new ILoad.ILoad0());
        instructionMap.put(0x1b, new ILoad.ILoad1());
        instructionMap.put(0x1c, new ILoad.ILoad2());
        instructionMap.put(0x1d, new ILoad.ILoad3());
        instructionMap.put(0x1e, new LLoad.LLoad0());
        instructionMap.put(0x1f, new LLoad.LLoad1());
        instructionMap.put(0x20, new LLoad.LLoad2());
        instructionMap.put(0x21, new LLoad.LLoad3());
        instructionMap.put(0x22, new FLoad.FLoad0());
        instructionMap.put(0x23, new FLoad.FLoad1());
        instructionMap.put(0x24, new FLoad.FLoad2());
        instructionMap.put(0x25, new FLoad.FLoad3());
        instructionMap.put(0x26, new DLoad.DLoad0());
        instructionMap.put(0x27, new DLoad.DLoad1());
        instructionMap.put(0x28, new DLoad.DLoad2());
        instructionMap.put(0x29, new DLoad.DLoad3());
        instructionMap.put(0x2a, new ALoad.ALoad0());
        instructionMap.put(0x2b, new ALoad.ALoad1());
        instructionMap.put(0x2c, new ALoad.ALoad2());
        instructionMap.put(0x2d, new ALoad.ALoad3());
        instructionMap.put(0x2e, new XALoad.IALoad());
        instructionMap.put(0x2f, new XALoad.LALoad());
        instructionMap.put(0x30, new XALoad.FALoad());
        instructionMap.put(0x31, new XALoad.DALoad());
        instructionMap.put(0x32, new XALoad.AALoad());
        instructionMap.put(0x33, new XALoad.BALoad());
        instructionMap.put(0x34, new XALoad.CALoad());
        instructionMap.put(0x35, new XALoad.SALoad());
        instructionMap.put(0x36, new IStore.IStoreI());
        instructionMap.put(0x37, new LStore.LStoreL());
        instructionMap.put(0x38, new FStore.FStoreF());
        instructionMap.put(0x39, new DStore.DStoreD());
        instructionMap.put(0x3a, new AStore.AStoreA());
        instructionMap.put(0x3b, new IStore.IStore0());
        instructionMap.put(0x3c, new IStore.IStore1());
        instructionMap.put(0x3d, new IStore.IStore2());
        instructionMap.put(0x3e, new IStore.IStore3());
        instructionMap.put(0x3f, new LStore.LStore0());
        instructionMap.put(0x40, new LStore.LStore1());
        instructionMap.put(0x41, new LStore.LStore2());
        instructionMap.put(0x42, new LStore.LStore3());
        instructionMap.put(0x43, new FStore.FStore0());
        instructionMap.put(0x44, new FStore.FStore1());
        instructionMap.put(0x45, new FStore.FStore2());
        instructionMap.put(0x46, new FStore.FStore3());
        instructionMap.put(0x47, new DStore.DStore0());
        instructionMap.put(0x48, new DStore.DStore1());
        instructionMap.put(0x49, new DStore.DStore2());
        instructionMap.put(0x4a, new DStore.DStore3());
        instructionMap.put(0x4b, new AStore.AStore0());
        instructionMap.put(0x4c, new AStore.AStore1());
        instructionMap.put(0x4d, new AStore.AStore2());
        instructionMap.put(0x4e, new AStore.AStore3());
        instructionMap.put(0x4f, new XAStore.IAStore());
        instructionMap.put(0x50, new XAStore.LAStore());
        instructionMap.put(0x51, new XAStore.FAStore());
        instructionMap.put(0x52, new XAStore.DAStore());
        instructionMap.put(0x53, new XAStore.AAStore());
        instructionMap.put(0x54, new XAStore.BAStore());
        instructionMap.put(0x55, new XAStore.CAStore());
        instructionMap.put(0x56, new XAStore.SAStore());
        instructionMap.put(0x57, new POP());
        instructionMap.put(0x58, new POP2());
        instructionMap.put(0x59, new DUP());
        instructionMap.put(0x5a, new DupX1());
        instructionMap.put(0x5b, new DupX2());
        instructionMap.put(0x5c, new DUP2());
        instructionMap.put(0x5d, new Dup2X1());
        instructionMap.put(0x5e, new Dup2X2());
        instructionMap.put(0x5f, new Swap());
        instructionMap.put(0x60, new ADD.IAdd());
        instructionMap.put(0x61, new ADD.LAdd());
        instructionMap.put(0x62, new ADD.FAdd());
        instructionMap.put(0x63, new ADD.DAdd());
        instructionMap.put(0x64, new SUB.ISub());
        instructionMap.put(0x65, new SUB.LSub());
        instructionMap.put(0x66, new SUB.FSub());
        instructionMap.put(0x67, new SUB.DSub());
        instructionMap.put(0x68, new MUL.IMul());
        instructionMap.put(0x69, new MUL.LMul());
        instructionMap.put(0x6a, new MUL.FMul());
        instructionMap.put(0x6b, new MUL.DMul());
        instructionMap.put(0x6c, new DIV.IDiv());
        instructionMap.put(0x6d, new DIV.LDiv());
        instructionMap.put(0x6e, new DIV.FDiv());
        instructionMap.put(0x6f, new DIV.DDiv());
        instructionMap.put(0x70, new REM.IRem());
        instructionMap.put(0x71, new REM.LRem());
        instructionMap.put(0x72, new REM.FRem());
        instructionMap.put(0x73, new REM.DRem());
        instructionMap.put(0x74, new NEG.INeg());
        instructionMap.put(0x75, new NEG.LNeg());
        instructionMap.put(0x76, new NEG.FNeg());
        instructionMap.put(0x77, new NEG.DNeg());
        instructionMap.put(0x78, new SH.ISHL());
        instructionMap.put(0x79, new SH.LSHL());
        instructionMap.put(0x7a, new SH.ISHR());
        instructionMap.put(0x7b, new SH.LSHR());
        instructionMap.put(0x7c, new SH.IUSHR());
        instructionMap.put(0x7d, new SH.LUSHR());
        instructionMap.put(0x7e, new AND.IAnd());
        instructionMap.put(0x7f, new AND.LAnd());
        instructionMap.put(0x80, new OR.IOR());
        instructionMap.put(0x81, new OR.LOR());
        instructionMap.put(0x82, new XOR.IXOR());
        instructionMap.put(0x83, new XOR.LXOR());
        instructionMap.put(0x84, new IInc());
        instructionMap.put(0x85, new I2X.I2L());
        instructionMap.put(0x86, new I2X.I2F());
        instructionMap.put(0x87, new I2X.I2D());
        instructionMap.put(0x88, new L2X.L2I());
        instructionMap.put(0x89, new L2X.L2F());
        instructionMap.put(0x8a, new L2X.L2D());
        instructionMap.put(0x8b, new F2X.F2I());
        instructionMap.put(0x8c, new F2X.F2L());
        instructionMap.put(0x8d, new F2X.F2D());
        instructionMap.put(0x8e, new D2X.D2I());
        instructionMap.put(0x8f, new D2X.D2L());
        instructionMap.put(0x90, new D2X.D2F());
        instructionMap.put(0x91, new I2X.I2B());
        instructionMap.put(0x92, new I2X.I2C());
        instructionMap.put(0x93, new I2X.I2S());
        instructionMap.put(0x94, new LCmp());
        instructionMap.put(0x95, new FCmp.FCmpL());
        instructionMap.put(0x96, new FCmp.FCmpG());
        instructionMap.put(0x97, new DCmp.DCmpL());
        instructionMap.put(0x98, new DCmp.DCmpG());
        instructionMap.put(0x99, new IfCond.IFEQ());
        instructionMap.put(0x9a, new IfCond.IFNE());
        instructionMap.put(0x9b, new IfCond.IFLT());
        instructionMap.put(0x9c, new IfCond.IFGE());
        instructionMap.put(0x9d, new IfCond.IFGT());
        instructionMap.put(0x9e, new IfCond.IFLE());
        instructionMap.put(0x9f, new IF_ICMP.IF_ICMPEQ());
        instructionMap.put(0xa0, new IF_ICMP.IF_ICMPNE());
        instructionMap.put(0xa1, new IF_ICMP.IF_ICMPLT());
        instructionMap.put(0xa2, new IF_ICMP.IF_ICMPGE());
        instructionMap.put(0xa3, new IF_ICMP.IF_ICMPGT());
        instructionMap.put(0xa4, new IF_ICMP.IF_ICMPLE());
        instructionMap.put(0xa5, new IF_ACMP.IF_ACMPEQ());
        instructionMap.put(0xa6, new IF_ACMP.IF_ACMPNE());
        instructionMap.put(0xa7, new Goto());
        instructionMap.put(0xaa, new TableSwitch());
        instructionMap.put(0xab, new LookupSwitch());
        instructionMap.put(0xac, new IReturn());
        instructionMap.put(0xad, new LReturn());
        instructionMap.put(0xae, new FReturn());
        instructionMap.put(0xaf, new DRetrun());
        instructionMap.put(0xb0, new AReturn());
        instructionMap.put(0xb1, new Retrun());
        instructionMap.put(0xb2, new GetStatic());
        instructionMap.put(0xb3, new PutStatic());
        instructionMap.put(0xb4, new GetField());
        instructionMap.put(0xb5, new PutField());
        instructionMap.put(0xb6, new InvokeVirtual());
        instructionMap.put(0xb7, new InvokeSpecial());
        instructionMap.put(0xb8, new InvokeStatic());
        instructionMap.put(0xb9, new InvokeInterface());
        instructionMap.put(0xbb, new New());
        instructionMap.put(0xbc, new NewArray());
        instructionMap.put(0xbd, new ANewArray());
        instructionMap.put(0xbe, new ArrayLength());
        instructionMap.put(0xbf, new AThrow());
        instructionMap.put(0xc0, new CheckCast());
        instructionMap.put(0xc1, new Instanceof());
        instructionMap.put(0xc2, new MonitorEnter());
        instructionMap.put(0xc3, new MonitorExit());
        instructionMap.put(0xc4, new Wide());
        instructionMap.put(0xc5, new MultiANewArray());
        instructionMap.put(0xc6, new IfNull());
        instructionMap.put(0xc7, new IfNotNull());
        instructionMap.put(0xc8, new GotoW());
        instructionMap.put(0xfe, new InvokeNative());
    }

    static Instruction newInstruction(int opcode) {
        Instruction instruction = instructionMap.get(opcode);
        if (instruction == null) {
            throw new UnsupportedOperationException(Integer.toHexString(opcode));
        }
        return instruction;
    }

}
