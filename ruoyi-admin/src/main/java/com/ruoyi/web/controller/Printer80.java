package com.ruoyi.web.controller;

import com.rongta.printer.pos80sdk.Pos80Printer;
import com.rongta.printer.pos80sdk.PrinterConnectParam;
import com.rongta.printer.pos80sdk.StyleConfig;
import com.rongta.printer.pos80sdk.PrinterConnectParam.ConnectType;
import com.rongta.printer.pos80sdk.StyleConfig.Align;
import com.rongta.printer.pos80sdk.StyleConfig.FontType;
import com.rongta.printer.pos80sdk.StyleConfig.HriPosition;
import com.rongta.printer.pos80sdk.StyleConfig.NVLogoType;
import java.util.ArrayList;

public class Printer80 {

    public static void main(String[] args) {
        StyleConfig config = new StyleConfig();
        PrinterConnectParam param = new PrinterConnectParam();
        param.setConnectType(ConnectType.PrinterDriver);
        param.setDevName("GTP-180 Printer");
        com.rongta.printer.pos80sdk.Pos80Printer srtPrinter = new com.rongta.printer.pos80sdk.Pos80Printer(config, param);
        srtPrinter.reset();
        srtPrinter.printText("sdafasd20459 245245 11111111245245", 0, 1, 1, FontType.STANDARD, 0);
        srtPrinter.printText("sdafasd20459 245245 11111111245245", 0, 2, 1, FontType.STANDARD, 8);
        srtPrinter.printText("sdafasd20459 245245 11111111245245", 10, 1, 2, FontType.STANDARD, 8);
        srtPrinter.printText("sdafasd20459 245245 11111111245245", 30, 1, 2, FontType.STANDARD, 128);
        srtPrinter.commitOperation();

        try {
//            srtPrinter.printBitmap("F:\\image\\1.bmp", Align.LEFT);
//            srtPrinter.printBitmap("F:\\image\\1_2.bmp", Align.RIGHT);
//            ArrayList<String> nvLogoFileList = new ArrayList();
//            nvLogoFileList.add("F:\\image\\1.bmp");
//            nvLogoFileList.add("F:\\image\\1_2.bmp");
//            srtPrinter.defineNVBitmap(nvLogoFileList);
            srtPrinter.printNVBitmap((byte)1, NVLogoType.NORMAL);
            srtPrinter.printNVBitmap((byte)1, NVLogoType.DOUBLE_HEIGHT);
            srtPrinter.printNVBitmap((byte)2, NVLogoType.DOUBLE_WIDTH);
            srtPrinter.printNVBitmap((byte)2, NVLogoType.DOUBLE_WIDTH_HEIGHT);
            srtPrinter.commitOperation();
//            srtPrinter.printBitmap("F:\\image\\2.bmp", Align.CENTER);
            srtPrinter.printBarcode("12345678912", 0, 73, 2, 162, FontType.STANDARD, HriPosition.BOTH);
            srtPrinter.feedLine(5);
            srtPrinter.cut(true, (byte)2);
            srtPrinter.commitOperation();
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }
}
