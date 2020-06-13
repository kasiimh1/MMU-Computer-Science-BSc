classdef mynb

    % This class aims to find the Naive Bayes from our provided data, 
    % where we fit all the data into a standardized range (normal distribution, no z-score needed here), 
    % classify the data (never slow) and predict the value for each example one by one iterating over the data, 
    % then we return our prediction which is based off the approximate true posterior probabilities for each class
    % calculate the likelihood which gives us a probability that a random class will belong to the example

    methods(Static)

        %train our own Naive Bayes model:
        function m = fit(train_examples, train_labels)

        %start of standardisation process 

            %store the diffrent train labels in our model example
            m.unique_classes = unique(train_labels);
            %store the value of the total amount of classes in our model example
            m.n_classes = length(m.unique_classes);

            %store the average in this model example
            m.means = {};
            %store the standard diviation in this model example
            m.stds = {};

            %Inside each iteration of the loop, it pulls out all of the training examples which belong to the current class**
            for i = 1:m.n_classes
                % set up the this_class variable as an example
                this_class = m.unique_classes(i);
                %find all the examples from this same class
                examples_from_this_class = train_examples{train_labels==this_class,:};
                %taking the caluclated mean value for each feature **
                m.means{end+1} = mean(examples_from_this_class);
                %taking the caluclated standard deviation for each feature **
                m.stds{end+1} = std(examples_from_this_class);
            
            end            
            % end of standardisation process

            %store the prior value for current class **
            m.priors = [];
            
            %Inside each iteration of the loop, it pulls out all of the training examples which belong to the current class**
            for i = 1:m.n_classes
                % set up the this_class variable as an example
                this_class = m.unique_classes(i);
                % find all the examples from this same class
                examples_from_this_class = train_examples{train_labels==this_class,:};
                % calculate the proir probality for this instance by dividing the total 
                % number of training examples by the how many times the class occurs
                m.priors(end+1) = size(examples_from_this_class,1) / size(train_labels,1);
            
			end

        end

        % create a new function that allows us to classify our testing data        
        function predictions = predict(m, test_examples)

            %set up the value to be categorically stored
            predictions = categorical;

            %create a new for loop where we can iterate over the testing examples **    
            for i=1:size(test_examples,1)

                %print the message below with the current value (item) we are currently on out of the size of the testing dataset e.g. current value is 2/50
                fprintf('classifying example %i/%i\n', i, size(test_examples,1));
                %take a value from our testing examples and store it 
                this_test_example = test_examples{i,:};
                %store a new value which holds the calculated predicted value for this one example/ predict a class label for this new example...
                this_prediction = mynb.predict_one(m, this_test_example);
                %add our newly calculated prediciton to be stored on the end **
                predictions(end+1) = this_prediction;
            
			end
        end

        %create a new fuction that allows us to predict only one example 
        function prediction = predict_one(m, this_test_example)

            %Inside each iteration of the loop, it pulls out all of the training examples which belong to the current class**
            for i=1:m.n_classes
                %store a new value which holds the calculated likelihood value for one example
                this_likelihood = mynb.calculate_likelihood(m, this_test_example, i);
                %store a new value which holds the calculated prior value for one example
                this_prior = mynb.get_prior(m, i);
                %store a new value which holds the calculated posterior value for one example
                %posterior_ - isn't equal to the posterior, but it is proportional to it
                posterior_(i) = this_likelihood * this_prior;
            
            end
            %what these two lines do is calculate numbers which approximate the true posterior probabilities for each class **
            %store the maxium value in the list because this tells us which element of unique_classes to look in for the name of the predicted class label **
            [winning_value_, winning_index] = max(posterior_);
            %store the prediction of the maxium value in the list from above 
            prediction = m.unique_classes(winning_index);

        end
        
        %create a function that allows us to give us a probability that a random class will belong to the example **
        function likelihood = calculate_likelihood(m, this_test_example, class)
            
            %set a starting value
			likelihood = 1;
            
            %create a new for loop where we can iterate over the total number of features **
            for i=1:length(this_test_example)
                %
                % We are calculating the likelihood in order to multiply it by the prior and give a value that is proportional to the posterior **
                %
                % Class conditional independence assumption is they're independant of one another if conditioned on the same class value
                % store the value of our previously set likelihood multiplied by probability density of the current class by passing in current index **
                % average and standard diviation 
                likelihood = likelihood * mynb.calculate_pd(this_test_example(i), m.means{class}(i), m.stds{class}(i));
            end
        end
        
        %create a function that fetches the prior for the current class
        function prior = get_prior(m, class)
            %returns the probalility from the model structure
			prior = m.priors(class);
        
		end
        
        %create a function that calaculates probability density for a normal distribution ** 
        function pd = calculate_pd(x, mu, sigma)
            first_bit = 1 / sqrt(2*pi*sigma^2);
            second_bit = - ( ((x-mu)^2) / (2*sigma^2) );
            pd = first_bit * exp(second_bit);
        
		end
            
    end
end
