package study.stackQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2021-01-25
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 * 프로그래머스 > 스택/큐 > 다리를 지나는 트럭
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 */
public class Lv2_TruckPassingBridge {
        public int solution(int bridge_length, int weight, int[] truck_weights) {
            int bridgeInTruckWeightSum = 0;
            int time =0;
            int truckNum =0;
            Queue<Truck> bridgeInTruck = new LinkedList<>();
            while(truckNum != truck_weights.length) {
                time++;
                for(Truck t : bridgeInTruck){t.distance++;}
                if(bridgeInTruck.peek() != null && bridgeInTruck.peek().distance > bridge_length)
                    bridgeInTruckWeightSum -= bridgeInTruck.poll().weight;
                if(bridgeInTruckWeightSum + truck_weights[truckNum] <= weight){
                    bridgeInTruck.add(new Truck(truck_weights[truckNum]));
                    bridgeInTruckWeightSum += truck_weights[truckNum++];
                }
            }
            time += bridge_length;
            return time;
        }
}
class Truck {
    int weight;
    int distance;
    public Truck(int weight) {
        this.weight = weight;
        this.distance = 1;
    }
}
