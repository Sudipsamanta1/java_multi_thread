class A
{
    int num;
    boolean valueSet=false;
    public synchronized void put(int num)
    {  
        System.out.println("in put");
         while(valueSet){
        try{ wait();
         }
        catch(Exception e){}
        }

        System.out.println("Putting value   :   "+num);
        this.num=num;
        valueSet=true;
        notify();
    }
    public synchronized void get()
    {
        while(!valueSet){
            try{
                 wait();
             }
            catch(Exception e){}
        }
        System.out.println("geting value :   "+num);
        valueSet=true;
        notify();
    }
}


class Producer implements Runnable
{
    A a;
    public Producer(A a){
        this.a=a;
        Thread t=new Thread(this,"Producer");
        t.start();
    }
    public void run(){
        int i=0;
        while(true)
        {
            a.put(i++);
            try{
                Thread.sleep(400);
            }
            catch(Exception e){}
        }
    }

}


class Consumer implements Runnable
{
    A a;
    public Consumer(A a){
        this.a=a;
        Thread t=new Thread(this,"Consumer");
        t.start();
    }
    public void run(){
        
        while(true)
        {
            a.get();
            try{
                Thread.sleep(1000);
            }
            catch(Exception e){

            }
        }
    }

}
 


public class producerConsumer
{
    public static void main(String[]args)
        {
            A a=new A();
            new Producer(a);
            new Consumer(a);
        }
}