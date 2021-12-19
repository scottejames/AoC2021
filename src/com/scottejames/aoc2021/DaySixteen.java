package com.scottejames.aoc2021;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static com.scottejames.aoc2021.StringHelper.*;


/*
    Nested packet...

 3 - VERSION
 3 - TYPE ID
 ID 4 - Literal Value, single binary number 4 bits padded with zero
      n? groups of five bits first two start with 1 last start with 0 (this tell you to top reading)
 110100101111111000101000 =
 VVVTTTAAAAABBBBBCCCCC

 Version 110
 Type    100
 A       10111
 B       11110
 C       00101
 IGNORE  000
 Number - 011111100101 = 2021

 NOT 4 represents an operator
    if lengthTypeID = 0 next 15 bits are a number total length in bits of the sub

    -> 912901337844
    -> 109318068
 */

public class DaySixteen {

    public static final int LITERAL_CHUNK_SIZE = 5;
    public static final int PACKET_SIZE_LENGTH = 15;
    public static final int SUB_PACKETS_LENGTH = 11;
    public static final int TYPE_LENGTH = 3;
    public static final int VERSION_LENGTH = 3;

    public static void main(String[] args){
        FileUtils fu = new FileUtils("2021/DaySixteen.txt");
        List<String> data = fu.getFileAsList();

        String input = data.get(0);
        String binInput = hexToBin(input);
        System.out.println(input);
        System.out.println(binInput);
        PacketData packetData = new PacketData(binInput);
        Packet packet = parsePacket(packetData);
        System.out.println(packet);
        System.out.println("Part 1 : " + packet.getVersionSum());
        System.out.println("Part 2 : " + packet.getValue());
    }
    public static Packet parsePacket(PacketData input) {
        Packet result;
        int version = binToDec(input.nextToken(VERSION_LENGTH));
        int typeId = binToDec(input.nextToken(TYPE_LENGTH));

        if (typeId == 4) {
            long value = readLiteralValue(input);
            result = new Packet(version,value,typeId);
        } else { // Operator
            String lengthTypeId = input.nextToken(1);
            result = new Packet(version,0,typeId);
            List<Packet> subPackets;
            if ("1".equals(lengthTypeId)) {
                subPackets = decodeSubPacketByAmount(input);
            } else {
                subPackets = decodeSubPacketByLength(input);
            }
            for(Packet packet: subPackets){
                result.addSubPacket(packet);
            }
        }

        return result;
    }
    private static long readLiteralValue(PacketData data) {
        StringBuilder value = new StringBuilder();
        char prefix;
        do {
            String chunk = data.nextToken(LITERAL_CHUNK_SIZE);
            value.append(chunk.substring(1));
            prefix = chunk.charAt(0);
        } while (prefix != '0');

        return binToLong(value.toString());
    }

    private static List<Packet> decodeSubPacketByLength(PacketData data){
        int length = binToDec(data.nextToken(PACKET_SIZE_LENGTH));
        PacketData subPacketData = new PacketData(data.nextToken(length));
        List<Packet> subPackets = new ArrayList<>();
        while (subPacketData.hasData()) {
            subPackets.add(parsePacket(subPacketData));
        }
        return subPackets;
    }
    private static List<Packet> decodeSubPacketByAmount(PacketData data){
        int amount = binToDec(data.nextToken(SUB_PACKETS_LENGTH));
        List<Packet> subPackets = new ArrayList<>(amount);

        for (int i = 0; i < amount; i++) {
            subPackets.add(parsePacket(data));
        }
        return subPackets;
    }

}
class  Packet {
    public static final int SUM = 0;
    public static final int PRODUCT = 1;
    public static final int MINIMUM = 2;
    public static final int MAXIMUM = 3;
    public static final int LITERAL = 4;
    public static final int GREATER_THAN = 5;
    public static final int LESS_THAN =  6;
    public static final int EQUAL_TO = 7;


    private int version;
    private long value;
    private int type;
    protected final List<Packet> subPackets = new ArrayList<>();


    Packet(int version, long value, int type){
        this.version = version;
        this.value = value;
        this.type = type;
    }
    public long getValue() {

        return switch (type) {
            case SUM -> subPackets.stream().mapToLong(p -> p.getValue()).sum();
            case PRODUCT -> subPackets.stream().mapToLong(p -> p.getValue()).reduce(1, (a, b) -> a * b);
            case MINIMUM -> subPackets.stream().mapToLong(p -> p.getValue()).min().getAsLong();
            case MAXIMUM -> subPackets.stream().mapToLong(p -> p.getValue()).max().getAsLong();
            case LITERAL -> value;
            case GREATER_THAN -> subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1l : 0l;
            case LESS_THAN -> subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1l : 0l;
            case EQUAL_TO -> subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1l : 0l;
            default -> throw new IllegalStateException("Invalid packet type " + type);
        };
    }

    public void addSubPacket(Packet packet) {
        subPackets.add(packet);
    }
    public long getVersionSum() {
        if (subPackets.size() ==0) {
            return version;

        } else {
            return version + subPackets.stream().mapToLong(Packet::getVersionSum).sum();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[Ver: " + version + "][Val: " + value + "][Tpe: " + type + "]\n\t");
        for(Packet p: subPackets){
            sb.append(p);
        }
        return sb.toString();
    }
}

class PacketData {
    private String data;
    public PacketData(String data){
        this.data = data;
    }
    public String nextToken(int size){
        String result = data.substring(0,size);
        data = data.substring(size);
        return result;
    }
    public boolean hasData(){
        return !data.isEmpty();
    }
}
