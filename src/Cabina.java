/**
Cabina: La Cabina es el carro del ascensor que sube y baja con personas.
La cabina tiene una botonera (que no se ocupará en esta etapa), una referencia
a la "caja ascensor" donde se encuentra, su posición actual, un indicador 
del piso en que se encuentra (requerido para mostrar piso actual) y una 
referencia al último sensor que activó y que al dejarlo debe desactivarlo.
La causa única de su movimiento es la invocación al método move.
En esta etapa la cabina se moverá en respuesta a las invoaciones a move.
 
Ante un movimiento, la cabina pregunta a la caja del ascensor por sensores en su nueva
posición.  De haber sensor, lo activa. Si no hay sensor se preocupa de desactivar
el último activado.
*/
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.*;  //for ActionListener, ActionEvent

public class Cabina {
   private BotoneraCabina botonera;
   private CajaAscensor shaft;
   private float position;  // in meters
   private int floorIndicator;
   private Sensor lastSensor=null;

   public Cabina (CajaAscensor cajaAscensor) {
      Random generator = new Random();
      shaft = cajaAscensor;
      position = generator.nextFloat();   //it starts between 0 an 1 [m]
      floorIndicator=1;  // to make it consistent with its position
   }
   public void move(float delta) {
      Sensor sensor;
      position += delta;
      sensor = shaft.findSensor(position);
      if (sensor != lastSensor) {
         if (sensor != null)
	        sensor.activateAction();
         if (lastSensor != null)
            lastSensor.deactivateAction();
	     lastSensor = sensor;
      }
   }
   public void setBotonera(BotoneraCabina bc){
      botonera = bc;
   }
   public float getPosition(){
      return position;
   }
   public int readFloorIndicator(){
      return floorIndicator;
   }
   public void setFloorIndicator(int currentFloor){
      floorIndicator = currentFloor;
   }
 }
