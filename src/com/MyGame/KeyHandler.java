package com.MyGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    MyGame gui;
    public KeyHandler(MyGame gui)
    {
        this.gui=gui;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD7)
        {
            gui.btns[0].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD8)
        {
            gui.btns[1].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD9)
        {
            gui.btns[2].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD4)
        {
            gui.btns[3].doClick();
        }if(e.getKeyCode()==KeyEvent.VK_NUMPAD5)
        {
            gui.btns[4].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD6)
        {
            gui.btns[5].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD3)
        {
            gui.btns[6].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD2)
        {
            gui.btns[7].doClick();
        }
        if(e.getKeyCode()==KeyEvent.VK_NUMPAD3)
        {
            gui.btns[8].doClick();
        }





    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
