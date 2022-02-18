package utils;

import java.awt.*;

/**
 * @author luoluo.hao
 * @create 2022-02-15 10:12
 **/
public class TypeRobot {
    private Robot robot;
    private int delay;

    //传入一个Robot对象
    public TypeRobot(Robot robot) {
        this.robot = robot;
    }

    //第二个参数是延时
    TypeRobot(Robot robot, int delay) {
        this.robot = robot;
        this.setDelay(delay);
    }

    void typeLowerCase(char c) {
        robot.keyPress(c-32);
        robot.keyRelease(c-32);
        delay();
    }

    void typeUpperCase(char c) {
        robot.keyPress(16);
        robot.keyPress(c);
        robot.keyRelease(16);
        robot.keyRelease(c);
        delay();
    }

    void typeNumber(char c) {
        robot.keyPress(c);
        robot.keyRelease(c);
        delay();
    }

    void typeOther(char c) {
        switch(c) {
            case '+':
                pressKeyWithCtrl(61);
                break;
            case '-':
                pressKey(45);
                break;
            case '*':
                pressKeyWithCtrl(56);
                break;
            case '/':
                pressKey(47);
                break;
            case '\'':
                pressKey(222);
                break;
            case ':':
                pressKeyWithCtrl(59);
                break;
            case '{':
                pressKeyWithCtrl(91);
                break;
            case '}':
                pressKeyWithCtrl(93);
                break;
            case '[':
                pressKey(91);
                break;
            case ']':
                pressKey(93);
                break;
            case ';':
                pressKey(59);
                break;
            case '#':
                pressKeyWithCtrl(51);
                break;
            case '!':
                pressKeyWithCtrl(49);
                break;
            case '%':
                pressKeyWithCtrl(53);
                break;
            case '&':
                pressKeyWithCtrl(55);
                break;
            case '=':
                pressKey(61);
                break;
            case ' ':
                pressKey(32);
                break;
            case '	':
                pressKey(9);
                break;
            case '\n':
                pressKey(10);
                break;
            case '<':
                pressKeyWithCtrl(44);
                break;
            case '>':
                pressKeyWithCtrl(46);
                break;
            case '?':
                pressKeyWithCtrl(47);
                break;
            case '.':
                pressKey(46);
                break;
            case '"':
                pressKeyWithCtrl(222);
                break;
            case '(':
                pressKeyWithCtrl(57);
                break;
            case ')':
                pressKeyWithCtrl(48);
                break;
            case '\\':
                pressKey(92);
                break;
            case ',':
                pressKey(44);
                break;
            case '@':
                pressKeyWithCtrl(50);
                break;
            case '|':
                pressKeyWithCtrl(92);
                break;
            case '^':
                pressKeyWithCtrl(54);
                break;
            case '_':
                pressKeyWithCtrl(45);
                break;
            default:
        }
    }

    public void setDelay(int delay) {
        this.delay = Math.max(delay, 0);
    }

    public int getDelay() {
        return delay;
    }

    private void delay() {
        robot.delay(delay);
    }

    private void pressKeyWithCtrl(int key) {
        robot.keyPress(16);
        robot.keyPress(key);
        robot.keyRelease(key);
        robot.keyRelease(16);
        delay();
    }

    private void pressKey(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
        delay();
    }
}
