package org.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.entities.Train;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Train_Service {

    private List<Train> trainList;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_DB_PATH = "../localDB/trains.json";

    public Train_Service() throws IOException {
        File trains = new File(TRAIN_DB_PATH);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {});
    }

    public List<Train> search_trains(String source, String destination) {
        return trainList.stream().filter(train -> valid_train(train, source, destination)).collect(Collectors.toList());
    }

    public void add_train(Train newTrain) {
        // Check if a train with the same trainId already exists
        Optional<Train> existingTrain = trainList.stream()
                .filter(train -> train.getTrain_id().equalsIgnoreCase(newTrain.getTrain_id()))
                .findFirst();

        if (existingTrain.isPresent()) {
            // If a train with the same trainId exists, update it instead of adding a new one
            update_train(newTrain);
        } else {
            // Otherwise, add the new train to the list
            trainList.add(newTrain);
            save_train_list_to_file();
        }
    }

    public void update_train(Train updatedTrain) {
        // Find the index of the train with the same trainId
        OptionalInt index = IntStream.range(0, trainList.size())
                .filter(i -> trainList.get(i).getTrain_id().equalsIgnoreCase(updatedTrain.getTrain_id()))
                .findFirst();

        if (index.isPresent()) {
            // If found, replace the existing train with the updated one
            trainList.set(index.getAsInt(), updatedTrain);
            save_train_list_to_file();
        } else {
            // If not found, treat it as adding a new train
            add_train(updatedTrain);
        }
    }

    private void save_train_list_to_file() {
        try {
            objectMapper.writeValue(new File(TRAIN_DB_PATH), trainList);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception based on your application's requirements
        }
    }

    private boolean valid_train(Train train, String source, String destination) {
        List<String> stationOrder = train.getTrain_stations();

        int sourceIndex = stationOrder.indexOf(source.toLowerCase());
        int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}