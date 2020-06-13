classdef mytree
    
    % This class aims to find the Decision Trees from our provided data, 
    % where we fit all the data (no z-score needed here and normal distribution probabilty is never 0), 
    % 

    methods(Static)
        % train our own Decision Tree model:
        function m = fit(train_examples, train_labels)
            emptyNode.number = []; %the (unique) number of the node within the overall tree structure
            emptyNode.examples = []; %any training examples and associated labels the node holds
            emptyNode.labels = [];
            emptyNode.prediction = []; %a prediction based on any class labels the node holds
            emptyNode.impurityMeasure = []; %a numeric measure of the impurity of any class labels held by a node (used in deciding whether to split it)
            emptyNode.children = {}; %if the decision is taken to split a node then it will store two child nodes and divide its training data between them

            emptyNode.splitFeature = []; %the particular feature (both the number of its column and the name) and the particular value of that feature, which define the split
            emptyNode.splitFeatureName = []; %the particular feature (both the number of its column and the name) and the particular value of that feature, which define the split
            emptyNode.splitValue = []; %the particular feature (both the number of its column and the name) and the particular value of that feature, which define the split
            m.emptyNode = emptyNode;  %copies the empty struc and creates a root node which is on the very top level of the decsion tree
            
            r = emptyNode; %set the root of the tree as the empty node with the stuc we have just defined
            r.number = 1; % set this to 1 as there's only one unique node currently in this tree
            r.labels = train_labels; %store the class label values in our model so we can access them later 
            r.examples = train_examples; %store the class example values in our model so we can access them later 
            r.prediction = mode(r.labels); %store the most common label for for predictions that might occur (or mode value)

            m.min_parent_size = 10; %set the min size of before considering splitting 
            m.unique_classes = unique(r.labels); %store the diffrent train labels in our model example
            m.feature_names = train_examples.Properties.VariableNames;
			m.nodes = 1;
            m.N = size(train_examples,1);

            m.tree = mytree.trySplit(m, r); %check nodes size to see if it can become a parent if so continues to split the tree
        end

        % function to generate all the possible splits from every unique value of every feature
        % perform an axis parallel spilt and only split if it will decrease the impurity of the associated labels

        function node = trySplit(m, node)

            %check if the current node is large enough to become a parent 
            if size(node.examples, 1) < m.min_parent_size
				return
			end

            node.impurityMeasure = mytree.weightedImpurity(m, node.labels);

            for i=1:size(node.examples,2)

				fprintf('evaluating possible splits on feature %d/%d\n', i, size(node.examples,2));
                
				[ps,n] = sortrows(node.examples,i);
                ls = node.labels(n);
                biggest_reduction(i) = -Inf;
                biggest_reduction_index(i) = -1;
                biggest_reduction_value(i) = NaN;
                for j=1:(size(ps,1)-1)
                    if ps{j,i} == ps{j+1,i}
                        continue;
                    end
                    
                    this_reduction = node.impurityMeasure - (mytree.weightedImpurity(m, ls(1:j)) + mytree.weightedImpurity(m, ls((j+1):end)));
                    
                    if this_reduction > biggest_reduction(i)
                        biggest_reduction(i) = this_reduction;
                        biggest_reduction_index(i) = j;
                    end
                end
				
            end

            [winning_reduction,winning_feature] = max(biggest_reduction);
            winning_index = biggest_reduction_index(winning_feature);

            if winning_reduction <= 0
                return
            else

                [ps,n] = sortrows(node.examples,winning_feature);
                ls = node.labels(n);

                node.splitFeature = winning_feature;
                node.splitFeatureName = m.feature_names{winning_feature};
                node.splitValue = (ps{winning_index,winning_feature} + ps{winning_index+1,winning_feature}) / 2;

                node.examples = [];
                node.labels = []; 
                node.prediction = [];

                node.children{1} = m.emptyNode;
                m.nodes = m.nodes + 1; 
                node.children{1}.number = m.nodes;
                node.children{1}.examples = ps(1:winning_index,:); 
                node.children{1}.labels = ls(1:winning_index);
                node.children{1}.prediction = mode(node.children{1}.labels);
                
                node.children{2} = m.emptyNode;
                m.nodes = m.nodes + 1; 
                node.children{2}.number = m.nodes;
                node.children{2}.examples = ps((winning_index+1):end,:); 
                node.children{2}.labels = ls((winning_index+1):end);
                node.children{2}.prediction = mode(node.children{2}.labels);
                
                node.children{1} = mytree.trySplit(m, node.children{1});
                node.children{2} = mytree.trySplit(m, node.children{2});
            end

        end
        
        function e = weightedImpurity(m, labels)

            weight = length(labels) / m.N;

            summ = 0;
            obsInThisNode = length(labels);
            for i=1:length(m.unique_classes)
                
				pc = length(labels(labels==m.unique_classes(i))) / obsInThisNode;
                summ = summ + (pc*pc);
            
			end
            g = 1 - summ;
            
            e = weight * g;

        end

        function predictions = predict(m, test_examples)

            predictions = categorical;
            
            for i=1:size(test_examples,1)
                
				fprintf('classifying example %i/%i\n', i, size(test_examples,1));
                this_test_example = test_examples{i,:};
                this_prediction = mytree.predict_one(m, this_test_example);
                predictions(end+1) = this_prediction;
            
			end
        end

        function prediction = predict_one(m, this_test_example)
            
			node = mytree.descend_tree(m.tree, this_test_example);
            prediction = node.prediction;
        
        end
        
        % decend tree until we reach a leaf node 
        function node = descend_tree(node, this_test_example)
            
			if isempty(node.children)
                return;
            else
                if this_test_example(node.splitFeature) < node.splitValue
                    node = mytree.descend_tree(node.children{1}, this_test_example);
                else
                    node = mytree.descend_tree(node.children{2}, this_test_example);
                end
            end
        
		end
        
        % describe a tree:
        % allows us to visualise the trained tree
        function describeNode(node)
            
			if isempty(node.children)
                fprintf('Node %d; %s\n', node.number, node.prediction);
            else
                fprintf('Node %d; if %s <= %f then node %d else node %d\n', node.number, node.splitFeatureName, node.splitValue, node.children{1}.number, node.children{2}.number);
                mytree.describeNode(node.children{1});
                mytree.describeNode(node.children{2});        
            end
        
		end
		
    end
end