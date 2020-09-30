/************************************
author: Thais Chloe Campanac-Climent
Project Name: Test Efficiency of Sorting Algorithms
Project Explained: Six sorting algorithms were given and it is up to the programmer to test the efficiency of these 
algorithms and making a GUI while making small improvements to the algorithms
Class Explained: This class is the GUI which handles all inputs and outputs by/for the user
Version: 1.8
Date: 9/18/20
*************************************/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GUI implements ActionListener, ChangeListener{
	
	//these are the types of sorts
	JButton insertionBut;
	JButton selectionBut;
	JButton quickBut;
	JButton mergeBut;
	JButton heapBut;
	JButton radixBut;
	
	//for the ultimate sorts
	JButton bestAlgoBut;
	
	//object for best algo class plus results
	BestAlgorithm decide;
	TextField originText;
	TextField whatListText;
	TextField whatSortText;
	TextField whatTimeText;
	TextField whatMovText;
	TextField whatCompText;
	
	//variables for Results GUI
	String sortTypeOutput;
	String listTypeOutput;
	TextField sortText;
	TextField listText;
	TextField sizeText;
	TextField timeText;
	TextField memText;
	TextField movText;
	TextField compText;
	
	//these are for the front end of the types of lists plus the frame
	JFrame frame;
	int inputvalue;
	TextField textField;
	
	//types of lists radio buttons
	JRadioButton inorderbut;
	JRadioButton reverseorderbut;
	JRadioButton almostinorderbut;
	JRadioButton randombut;
	
	//creation button which will execute the creation of a list
	JButton createBut;
	Boolean wasCreated = false;
	
	//creating the different types of lists chosen by the user
	int[] inputlist;
	static Integer[] inputList2; //for the heap
	int[] resetList; //when the list needs to be reset for the next sorting algorithm
	
	//objects for the sort classes
	InsertionSort insertionSortList = new InsertionSort();
	SelectionSort selectionSortList = new SelectionSort();
	QuickSort quickSortList = new QuickSort();
	MergeSort mergeSortList = new MergeSort();
	RadixSort radixSortList = new RadixSort();
	Heap heapSortList = new Heap();
	
	
	//time probe for the sort methods
	long startTime;
	long endTime;
	long durationInNano;
	double mov = 0;
	double comp = 0;
	//memory probe for the sort methods
	long beforeUsedMem;
	long afterUsedMem;
	long actualMemUsed;
	
	//elements to find the winning algorithms
	TextField winnertext;
	long pastTimeRecord = 1000000000;
	long pastMemRecord = 1000000000;
	String pastSortRecord;
	
	//in order list method
	public void inorderlist() {
		inputlist = new int[inputvalue];
		resetList = new int[inputvalue];
		for(int x = 0; x < inputvalue; x++) {
			inputlist[x] = x;
			resetList[x] = x;
		}
	}
	
	//reverse list method
	public void reverseorderlist() {
		inputlist = new int[inputvalue];
		resetList = new int[inputvalue];
		int counter = 0;
		for(int x = inputvalue; x > 0; x--) {
			inputlist[counter] = x;
			resetList[counter] = x;
			counter++;
		}
	}
	
	//almost in order list method
	public void almostorderlist() {
		inputlist = new int[inputvalue];
		resetList = new int[inputvalue];
		for(int x = 0; x < inputvalue; x++) {
			inputlist[x] = x;
			resetList[x] = x;
		}
		
		//making it 20% unordered
		int numbervarchan = (int)(.4 * inputvalue);

		for(int x = numbervarchan; x > 0; x--) {
			int changevar =(int)(Math.random()*inputvalue);
			inputlist[x] = changevar;
			resetList[x] = changevar;
		}
	}
	
	//random list method
	public void randomlist() {
		inputlist = new int[inputvalue];
		resetList = new int[inputvalue];
		for(int x = 0; x < inputvalue; x++) {
			inputlist[x]= (int) (Math.random() *inputvalue);
			int var = inputlist[x];
			resetList[x] = var;
		}
	}

	public GUI() {
		//making the frame of the window
		frame = new JFrame();
		
		//making the buttons for the types of sorting and leading them to execute
		insertionBut = new JButton("Insertion Sort");
		insertionBut.addActionListener(this);
		
		selectionBut = new JButton("Selection Sort");
		selectionBut.addActionListener(this);
		
		quickBut = new JButton("Quick Sort");
		quickBut.addActionListener(this);
		
		mergeBut = new JButton("Merge Sort");
		mergeBut.addActionListener(this);
		
		heapBut = new JButton("Heap Sort");
		heapBut.addActionListener(this);
		
		radixBut = new JButton("Radix Sort");
		radixBut.addActionListener(this);
		
		
		//making the panel for the algorithms button
		JPanel sortAlgoPanel = new JPanel();
		sortAlgoPanel.setBorder(BorderFactory.createTitledBorder("Algorthims"));
		sortAlgoPanel.setLayout(new GridLayout(0, 1));
		
		//adding the sort buttons to the panel
		sortAlgoPanel.add(insertionBut);
		sortAlgoPanel.add(selectionBut);
		sortAlgoPanel.add(quickBut);
		sortAlgoPanel.add(mergeBut);
		sortAlgoPanel.add(heapBut);
		sortAlgoPanel.add(radixBut);
		
		//for the best for worst algorithms options
		bestAlgoBut = new JButton("Choose Best Algo");
		bestAlgoBut.addActionListener(this);
		
		JPanel chooseForMe = new JPanel();
		chooseForMe.setLayout(new GridLayout(0,1));
		chooseForMe.add(bestAlgoBut);
		
		//making another tab for best or worst algorithm
		JTabbedPane ultimate = new JTabbedPane();
		ultimate.add("Sorts", sortAlgoPanel);
		ultimate.add("Choose For Me", chooseForMe);
		
		
		//outline for the winning algorithm panel
		JPanel winner = new JPanel();
		winner.setBorder(BorderFactory.createTitledBorder("Winning Algorithm"));
		winner.add(Box.createRigidArea(new Dimension(50, 25)));
		//winner.setBounds(50, 0, 50, 25);
		winnertext = new TextField("            ");
		JLabel winLabel = new JLabel("Winner: ");
		winLabel.setLabelFor(winnertext);
		winner.add(winLabel);
		winner.add(winnertext);
	
		
		//new panel for list properties
		JPanel listCreationPanel = new JPanel();
		listCreationPanel.setLayout(new GridLayout(0, 2));
		listCreationPanel.setBorder(BorderFactory.createTitledBorder("List Properties"));
		
		//creating the buttons
		ButtonGroup listTypes = new ButtonGroup();
		inorderbut = new JRadioButton("InOrder");
		reverseorderbut= new JRadioButton("ReverseOrder");
		almostinorderbut = new JRadioButton("AlmostOrder");
		randombut = new JRadioButton("Random");
		
		listTypes.add(inorderbut);
		listTypes.add(reverseorderbut);
		listTypes.add(almostinorderbut);
		listTypes.add(randombut);
		
		//adding the types of lists into the list panel
		listCreationPanel.add(inorderbut);
		listCreationPanel.add(reverseorderbut);
		listCreationPanel.add(almostinorderbut);
		listCreationPanel.add(randombut);
		
		//creating the slider to input the value of the list
		JSlider listSize = new JSlider(JSlider.HORIZONTAL, 1000, 50000, 2400);
		textField = new TextField();
		listSize.addChangeListener(this);
		listSize.setMajorTickSpacing(1000);
		listSize.setMinorTickSpacing(100);
		listSize.setPaintTicks(true);
		listSize.setPaintLabels(false);
		
		createBut = new JButton("Create The List");
		createBut.addActionListener(this);
		
		//Outline for the Results Panel
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
		resultsPanel.setLayout(new GridLayout(0,2));
		
		//output sort type
		JLabel sortType = new JLabel("Sort Type: ");
		sortText = new TextField("         ");
		sortType.setLabelFor(sortText);
		resultsPanel.add(sortType);
		resultsPanel.add(sortText);
		
		//output for type of list
		JLabel listType = new JLabel("List Type: ");
		listText = new TextField("        ");
		listType.setLabelFor(listText);
		resultsPanel.add(listType);
		resultsPanel.add(listText);
		
		//output size of list
		JLabel sizeLabel = new JLabel("N: ");
		sizeText = new TextField("          ");
		sizeLabel.setLabelFor(sizeText);
		resultsPanel.add(sizeLabel);
		resultsPanel.add(sizeText);
		
		//output time for algo
		JLabel timeLabel = new JLabel("Time(NS): ");
		timeText = new TextField("          ");
		timeLabel.setLabelFor(timeText);
		resultsPanel.add(timeLabel);
		resultsPanel.add(timeText);
		
		//output memory for algo
		JLabel memLabel = new JLabel("Memory Used(B): ");
		memText = new TextField("          ");
		memLabel.setLabelFor(memText);
		resultsPanel.add(memLabel);
		resultsPanel.add(memText);
		
		//output movement for algo
		JLabel movLabel = new JLabel("Movements: ");
		movText = new TextField("          ");
		movLabel.setLabelFor(movText);
		resultsPanel.add(movLabel);
		resultsPanel.add(movText);
		
		//output comparison for also
		JLabel compLabel = new JLabel("Comparisons: ");
		compText = new TextField("          ");
		compLabel.setLabelFor(compText);
		resultsPanel.add(compLabel);
		resultsPanel.add(compText);
		
		//outline for the results of the best algorithms button
		JPanel resultBest = new JPanel();
		resultBest.setLayout(new GridLayout(0,2));
		
		//Original type of list
		JLabel originList = new JLabel("Original List Type: ");
		originText = new TextField("       ");
		originList.setLabelFor(originText);
		resultBest.add(originList);
		resultBest.add(originText);
		//decided type of list
		JLabel whatList = new JLabel("Type Of List Calculated: ");
		whatListText = new TextField();
		whatList.setLabelFor(whatListText);
		resultBest.add(whatList);
		resultBest.add(whatListText);
		//decided type of sort to use
		JLabel whatSort = new JLabel("Best Sort for List: ");
		whatSortText = new TextField();
		whatSort.setLabelFor(whatSortText);
		resultBest.add(whatSort);
		resultBest.add(whatSortText);
		//time
		JLabel whatTime = new JLabel("Time (NS): ");
		whatTimeText = new TextField();
		whatTime.setLabelFor(whatTimeText);
		resultBest.add(whatTime);
		resultBest.add(whatTimeText);
		//movement
		JLabel whatMov = new JLabel("Movement: ");
		whatMovText = new TextField();
		whatMov.setLabelFor(whatMovText);
		resultBest.add(whatMov);
		resultBest.add(whatMovText);
		//comparisons
		JLabel whatComp = new JLabel("Comp: ");
		whatCompText = new TextField();
		whatComp.setLabelFor(whatCompText);
		resultBest.add(whatComp);
		resultBest.add(whatCompText);
		
		
		JTabbedPane results = new JTabbedPane();
		results.add("Sort Results", resultsPanel);
		results.add("Best Results", resultBest);
		
		listCreationPanel.add(listSize);
		listCreationPanel.add(textField,BorderLayout.CENTER);
		listCreationPanel.add(createBut, BorderLayout.SOUTH);
		
		//panel for the East Panel
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		
		eastPanel.add(winner, c);
		c.gridy = 3;
		c.gridheight = 15;
		eastPanel.add(listCreationPanel, c);
		c.gridy = 19;
		c.gridheight = 25;
		eastPanel.add(results, c);
		
		//make the panel apart of the frame
		//frame.add(sortAlgoPanel, BorderLayout.WEST);
		frame.add(ultimate, BorderLayout.WEST);
		frame.add(eastPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Algorithms Testing Center");
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new GUI();	
	}

	//this will perform the button actions
	public void actionPerformed(ActionEvent e) {
		
		// insertion sort algorithm execution
		if(e.getSource()==insertionBut) {
			if(wasCreated) {
				sortTypeOutput = "Insertion Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				insertionSortList.insertionSort(inputlist);
				endTime = System.nanoTime();
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		// selection sort algorithm execution
		if(e.getSource()==selectionBut) {
			if(wasCreated) {
				sortTypeOutput = "Selection Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				selectionSortList.selectionSort(inputlist); 
				endTime = System.nanoTime();
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		// quick sort algorithm execution
		if(e.getSource()==quickBut) {
			if(wasCreated) {
				sortTypeOutput = "Quick Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				quickSortList.quickSort(inputlist);
				endTime = System.nanoTime();
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		//heap sort algorithm execution
		if(e.getSource()==heapBut) {
			if(wasCreated) {
				covertList();
				sortTypeOutput = "Heap Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				HeapSort.heapSort(inputList2);
				endTime = System.nanoTime();
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		// merge sort execution algorithm
		if(e.getSource()==mergeBut) {
			if(wasCreated) {
				sortTypeOutput = "Merge Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				mergeSortList.mergeSort(inputlist);
				endTime = System.nanoTime();
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		// radix sort execution algorithm
		if(e.getSource()==radixBut) {
			if(wasCreated) {
				sortTypeOutput = "Radix Sort";
				sortText.setText(sortTypeOutput);
				beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				startTime = System.nanoTime();
				radixSortList.radixSort(inputlist, inputvalue);
				endTime = System.nanoTime();
				durationInNano = endTime-startTime;
				afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				showResults();
			}
			else
				error();
		}
		
		if(e.getSource()==createBut) {
			wasCreated = true;
			if(inorderbut.isSelected()) {
				nullList();
				listTypeOutput = "In Order";
				inorderlist();
			}
			if(reverseorderbut.isSelected()) {
				nullList();
				listTypeOutput = "Reverse Order";
				reverseorderlist();
			}
			if(almostinorderbut.isSelected()) {
				nullList();
				listTypeOutput = "Almost In Order";
				almostorderlist();
			}
			if(randombut.isSelected()) {
				nullList();
				listTypeOutput = "Random";
				randomlist();
			}
		}
		
		if(e.getSource() == bestAlgoBut) {
			if(wasCreated) {
				decide = new BestAlgorithm();
				decide.findBestAlgo(inputlist, inputvalue);
				sortTypeOutput = "Other Tab";
				sortText.setText("Check Other Tab");
				showResults();
			}
			else
				error();
		}
		
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// to update the slider and the text field that is next to it
		JSlider source = (JSlider)e.getSource();
		if(!source.getValueIsAdjusting()) {
			inputvalue = (int)source.getValue();
			textField.setText(" "+ source.getValue());
		}
	}

	void covertList() {
		//to make the list go to the heap classes
		inputList2 = new Integer[inputlist.length];
		
		for(int x = 0; x < inputvalue; x++) {
			inputList2[x] = inputlist[x];
		}
		
	}

	private void showResults() {
		// this shows the results of what the algorithms have done
		//updating the Results GUI
		if(sortTypeOutput.equals("Insertion Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			durationInNano = endTime - startTime;
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Long.toString(insertionSortList.getMov()));
			compText.setText(Integer.toString(insertionSortList.getComp()));
		}
		
		if(sortTypeOutput.equals("Selection Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			durationInNano = endTime - startTime;
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Long.toString(selectionSortList.getMov()));
			compText.setText(Integer.toString(selectionSortList.getComp()));
		}
		
		if(sortTypeOutput.equals("Quick Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			durationInNano = endTime - startTime;
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Integer.toString(quickSortList.getMov()));
			compText.setText(Integer.toString(quickSortList.getComp()));
		}
		
		if(sortTypeOutput.equals("Heap Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			durationInNano = endTime - startTime;
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Integer.toString(heapSortList.getMov()));
			compText.setText(Integer.toString(heapSortList.getComp()));
		}
		
		if(sortTypeOutput.equals("Merge Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			durationInNano = endTime - startTime;
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Integer.toString(mergeSortList.getMov()));
			compText.setText(Integer.toString(mergeSortList.getComp()));
		}
		
		if(sortTypeOutput.equals("Radix Sort")) {
			listText.setText(listTypeOutput);
			sizeText.setText(Integer.toString(inputvalue));
			timeText.setText(Long.toString(durationInNano));
			actualMemUsed = afterUsedMem - beforeUsedMem;
			memText.setText(Long.toString(actualMemUsed));
			movText.setText(Integer.toString(radixSortList.getMov()));
			compText.setText(Integer.toString(radixSortList.getComp()));
		}
		
		if(sortTypeOutput.contentEquals("Other Tab")){
			originText.setText(listTypeOutput);
			whatListText.setText(decide.typeList);
			whatSortText.setText(decide.bestAlgo);
			whatTimeText.setText(Long.toString(decide.durationInNano));
			whatMovText.setText(Long.toString(decide.mov));
			whatCompText.setText(Long.toString(decide.comp));
		}
		
		//reset the array to be empty
		//need to reset all objects and find winning algorithm
		findingWinningAlgo();
		resetEverything();
	}
	
	public void error() {
		Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
		JOptionPane.showMessageDialog(frame, "No List Created \n Go to the List Properties section", null, JOptionPane.ERROR_MESSAGE, errorIcon);
	}
	
	//updates the winning algorithm GUI panel and decide the winner
	public void findingWinningAlgo() {
			//see if the memCall variable can decide the winner
			if(pastTimeRecord > durationInNano) {
				winnertext.setText(sortTypeOutput);
				pastSortRecord = sortTypeOutput;
				pastTimeRecord = durationInNano;
				pastMemRecord = actualMemUsed;
			}
			//if not then see if there needs to be a tie breaker
			else if(pastTimeRecord == durationInNano) {
				//tie breaker using time
				if(pastMemRecord > actualMemUsed) {
					winnertext.setText(sortTypeOutput);
					pastSortRecord = sortTypeOutput;
					pastTimeRecord = durationInNano;
					pastMemRecord = actualMemUsed;
				}
			}
	}
	
	//this resets all objects to continuously reuse
	public void resetEverything() {	
		for(int x = 0; x < inputvalue; x++) {
			inputlist[x] = resetList[x];
		}
		
		insertionSortList = new InsertionSort();
		selectionSortList = new SelectionSort();
		quickSortList = new QuickSort();

		Heap temp3 = new Heap();
		temp3.resetMov();
		temp3.resetComp();
		
		mergeSortList = new MergeSort();
		radixSortList = new RadixSort();
		decide = new BestAlgorithm();
		
	}
	
	public void nullList() {
		int[] temp = null;
		Integer[] temp2 = null;
		inputlist = temp;
		inputList2 = temp2;
		
		pastMemRecord = 1000000000;
		pastTimeRecord = 1000000000;
	}

}
