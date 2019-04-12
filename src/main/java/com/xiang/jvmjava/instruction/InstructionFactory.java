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

/**
 * @author 项三六
 * @time 2019/4/2 9:42
 * @comment
 */

class InstructionFactory {

    static Instruction newInstruction(int opcode) {
        switch (opcode) {
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
            case 0x12:
                return new LDC();
            case 0x13:
                return new LDCW();
            case 0x14:
                return new LDC2W();
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
            case 0x2e:
                return new XALoad.IALoad();
            case 0x2f:
                return new XALoad.LALoad();
            case 0x30:
                return new XALoad.FALoad();
            case 0x31:
                return new XALoad.DALoad();
            case 0x32:
                return new XALoad.AALoad();
            case 0x33:
                return new XALoad.BALoad();
            case 0x34:
                return new XALoad.CALoad();
            case 0x35:
                return new XALoad.SALoad();
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
            case 0x4f:
                return new XAStore.IAStore();
            case 0x50:
                return new XAStore.LAStore();
            case 0x51:
                return new XAStore.FAStore();
            case 0x52:
                return new XAStore.DAStore();
            case 0x53:
                return new XAStore.AAStore();
            case 0x54:
                return new XAStore.BAStore();
            case 0x55:
                return new XAStore.CAStore();
            case 0x56:
                return new XAStore.SAStore();
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
            case 0x85:
                return new I2X.I2L();
            case 0x86:
                return new I2X.I2F();
            case 0x87:
                return new I2X.I2D();
            case 0x88:
                return new L2X.L2I();
            case 0x89:
                return new L2X.L2F();
            case 0x8a:
                return new L2X.L2D();
            case 0x8b:
                return new F2X.F2I();
            case 0x8c:
                return new F2X.F2L();
            case 0x8d:
                return new F2X.F2D();
            case 0x8e:
                return new D2X.D2I();
            case 0x8f:
                return new D2X.D2L();
            case 0x90:
                return new D2X.D2F();
            case 0x91:
                return new I2X.I2B();
            case 0x92:
                return new I2X.I2C();
            case 0x93:
                return new I2X.I2S();
            case 0x94:
                return new LCmp();
            case 0x95:
                return new FCmp.FCmpL();
            case 0x96:
                return new FCmp.FCmpG();
            case 0x97:
                return new DCmp.DCmpL();
            case 0x98:
                return new DCmp.DCmpG();
            case 0x99:
                return new IfCond.IFEQ();
            case 0x9a:
                return new IfCond.IFNE();
            case 0x9b:
                return new IfCond.IFLT();
            case 0x9c:
                return new IfCond.IFGE();
            case 0x9d:
                return new IfCond.IFGT();
            case 0x9e:
                return new IfCond.IFLE();
            case 0x9f:
                return new IF_ICMP.IF_ICMPEQ();
            case 0xa0:
                return new IF_ICMP.IF_ICMPNE();
            case 0xa1:
                return new IF_ICMP.IF_ICMPLT();
            case 0xa2:
                return new IF_ICMP.IF_ICMPGE();
            case 0xa3:
                return new IF_ICMP.IF_ICMPGT();
            case 0xa4:
                return new IF_ICMP.IF_ICMPLE();
            case 0xa5:
                return new IF_ACMP.IF_ACMPEQ();
            case 0xa6:
                return new IF_ACMP.IF_ACMPNE();
            case 0xa7:
                return new Goto();
            // case 0xa8:
            // 	return &JSR{}
            // case 0xa9:
            // 	return &RET{}
            case 0xaa:
                return new TableSwitch();
            case 0xab:
                return new LookupSwitch();
            case 0xac:
                return new IReturn();
            case 0xad:
                return new LReturn();
            case 0xae:
                return new FReturn();
            case 0xaf:
                return new DRetrun();
            case 0xb0:
                return new ARetrun();
            case 0xb1:
                return new Retrun();
            case 0xb2:
                return new GetStatic();
            case 0xb3:
                return new PutStatic();
            case 0xb4:
                return new GetField();
            case 0xb5:
                return new PutField();
            case 0xb6:
                return new InvokeVirtual();
            case 0xb7:
                return new InvokeSpecial();
            case 0xb8:
                return new InvokeStatic();
            case 0xb9:
                return new InvokeInterface();
            // case 0xba:
            // 	return &INVOKE_DYNAMIC{}
            case 0xbb:
                return new New();
            case 0xbc:
                return new NewArray();
            case 0xbd:
                return new ANewArray();
            case 0xbe:
                return new ArrayLength();
            case 0xbf:
                // 	return athrow
            case 0xc0:
                return new CheckCast();
            case 0xc1:
                return new Instanceof();
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case 0xc4:
                return new Wide();
            case 0xc5:
                return new MultiANewArray();
            case 0xc6:
                return new IfNull();
            case 0xc7:
                return new IfNotNull();
            case 0xc8:
                return new GotoW();
            // case 0xc9:
            // 	return &JSR_W{}
            // case 0xca: breakpoint
            case 0xfe:
                return new InvokeNative();
            // case 0xff: impdep2
            default:
                System.out.println(Integer.toHexString(opcode));
                throw new UnsupportedOperationException();
        }
    }

}
