classdef myknn

% This class aims to find the K nearest neighbours from our provided data, 
% where we fit all the data into a standardized range by setting a z-score (as small K could overfit training data), 
% classify the data (to the nearest neighbours & this is slower the more testing examples they're) 'classify is K * training examples' and predict the value for each example one by one iterating over the data, 
% now we calculate the distance between each specific example and sort them closest to our K value ... continued next line
% (similarity can be measured here.. between examples plotted in the feature space), 
% then we return our prediction which is based off the mode of nearest neighbours to K or K-NN 
% You want the value of K to be a small as possible (but this could also overfit the training data).

    methods(Static)
        
        % create a new function that allows us to train K-NN classifier from the provided data by only passing a hyper parameter (K)       
        function m = fit(train_examples, train_labels, k)
          
        % start of standardisation process 

        %in this process we formulate a standard range for all the data that was inputted for our fit method 
        
            %we now find the average (mean) for all the inputted data stored in our array by iterating shorthand over it and store the result
            m.mean = mean(train_examples{:,:});
            %we now find the standard deviation for all the inputted data stored in our array by iterating shorthand over it, We store the result
			m.std = std(train_examples{:,:}); 
            
            %create a new for loop where we can iterate over the training data and take away the averages 
            % and then divide by the standard range we worked out in the step above
            for i=1:size(train_examples,1)
                %take the current value and minus the average that we worked out above and store the result
				train_examples{i,:} = train_examples{i,:} - m.mean;
                %take the current value and divide the average that we worked out above and store the result
                train_examples{i,:} = train_examples{i,:} ./ m.std;
            end
        % end of standardisation process
            
            %store the class example values in our model so we can access them later 
            m.train_examples = train_examples;
            %store the class label values in our model so we can access them later 
            m.train_labels = train_labels;
            %store our value of K or the value which we will compare the nearest neighbours with 
            m.k = k;        
        end
        
        % create a new function that allows us to classify our testing data        
        function predictions = predict(m, test_examples)

            %set up the value to be categorically stored
            predictions = categorical;

            %create a new for loop where we can iterate over all of the testing examples     
            for i=1:size(test_examples,1)
                
                %print the message below with the current value (item) we are currently on out of the size of the testing dataset e.g. current value is 2/50
                fprintf('classifying example example %i/%i\n', i, size(test_examples,1));
                
                %take a value from our testing examples and store it 
                this_test_example = test_examples{i,:};
                
                % start of standardisation process

                %in this process we formulate a standard range for all the data that was inputted for our fit method 

                %take the value of our testing data and minus the average that we worked out in our fit function and store the result
                this_test_example = this_test_example - m.mean;
                %take the value of our testing data and divide the average that we worked out in our fit function and store the result
                this_test_example = this_test_example ./ m.std;
                % end of standardisation process
                
                %store a new value which holds the calculated predicted value for this one example
                this_prediction = myknn.predict_one(m, this_test_example);
                %taking the predicted example above we then add our newly calculated prediciton to be stored on the end
                predictions(end+1) = this_prediction;            
            end
        
		end

        %create a new fuction that allows us to predict only one example 
        function prediction = predict_one(m, this_test_example)
            
            %store a new value which holds the calculated distances values for one example
            distances = myknn.calculate_distances(m, this_test_example);
            %store the value of the K nearest for this example 
            neighbour_indices = myknn.find_nn_indices(m, distances);
            %store a new value which holds the calculated prediction value for one example
            prediction = myknn.make_prediction(m, neighbour_indices);
        
        end

        %create a new function that loops through all the stored training examples in our model
        % and calculates the straight line distance between two points for all the training examples to our one example
        function distances = calculate_distances(m, this_test_example)
            %store our calcluated distances for this example
			distances = [];
            
            %create a new for loop where we can iterate over all of the training examples  
			for i=1:size(m.train_examples,1)
                %store the value for this example from this row and column
                this_training_example = m.train_examples{i,:};
                %store the calculated distance between this train example and the test example
                this_distance = myknn.calculate_distance(this_training_example, this_test_example);
                %taking the distance example above we then add our newly calculated distance to be stored on the end
                distances(end+1) = this_distance;
            end
        
        end
        
        % create a new function that allows us to find the calculated distance between two points 
        function distance = calculate_distance(p, q)       

            %store the value of the difference between the two points
            % so we can use that value below
            differences = q - p;

            %store the value of the diffrence between the two points
            % to the power of 2, this converts all values to become postive so we can indentify all the diffrences
            % within a more precise range as some values could return 0 or less 
            squares = differences .^ 2;

            %calculate the total of all the values held in this example
            total = sum(squares);

            %store the total disantance of the example provided by finding the square root
            distance = sqrt(total);        
		end

        %create a new function that stores the indices of the k nearest neighbours from the provided example in a sorted format
        function neighbour_indices = find_nn_indices(m, distances)
            % store the value of the sorted distances in this example in a sorted list from values that we worked out in our function distance above
            [sorted, indices] = sort(distances);
            % store the first K value of the sorted list from above 
            neighbour_indices = indices(1:m.k);
        
		end
        
        %create a new function that makes a prediction based on the values from our model calculating the most common value and store them
        function prediction = make_prediction(m, neighbour_indices)
            %store the labels for the values which are closest to our k example 
            neighbour_labels = m.train_labels(neighbour_indices);
            %store the most common value for this example (or mode value)
            prediction = mode(neighbour_labels);        
		end

    end
end
