package standard.clone;

public class DeepCopy {
  
  public static void main(String[] args) {
    OceanReading reading = new OceanReading(1.0,2.0);
    OceanReading reading1 = (OceanReading)reading.clone();
  }
  
}
