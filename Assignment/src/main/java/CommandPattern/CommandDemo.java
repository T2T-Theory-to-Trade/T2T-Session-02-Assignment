package CommandPattern;

//Invoker
class RemoteControl{
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }
    public void pressButton(){
        command.execute();
    }

    public void pressUndo(){
        command.undo();
    }
}




public class CommandDemo {
    public static void main(String[] args){
        Light roomlight = new Light();

        Command lightOn = new LightOnCommand(roomlight);
        Command lightOff = new LightOffCommand(roomlight);

        Fan roomFan = new Fan();

        Command fanOn = new FanOnCommand(roomFan);
        Command fanOff = new FanOffCommand(roomFan);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(lightOn);
        remote.pressButton();
        remote.setCommand(fanOn);
        remote.pressButton();
        remote.setCommand(lightOff);
        remote.pressButton();
        remote.setCommand(fanOff);
        remote.pressButton();

        //Undo feature
        remote.pressUndo();
    }
}
