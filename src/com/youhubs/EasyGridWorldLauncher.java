package com.youhubs;

import com.youhubs.util.AnalysisAggregator;
import com.youhubs.util.AnalysisRunner;
import com.youhubs.util.BasicRewardFunction;
import com.youhubs.util.BasicTerminalFunction;
import com.youhubs.util.MapPrinter;
import burlap.oomdp.core.Domain;
import burlap.oomdp.core.TerminalFunction;
import burlap.oomdp.core.states.State;
import burlap.oomdp.singleagent.RewardFunction;
import burlap.oomdp.singleagent.environment.SimulatedEnvironment;
import burlap.oomdp.singleagent.explorer.VisualExplorer;
import burlap.oomdp.visualizer.Visualizer;

public class EasyGridWorldLauncher {

	private static boolean visualizeInitialGridWorld = true;
	private static boolean runValueIteration = true;
	private static boolean runPolicyIteration = true;
	private static boolean runQLearning = true;
	
	private static Integer MAX_ITERATIONS = 200;
	private static Integer NUM_INTERVALS = 100;

	protected static int[][] map = new int[][] { 
			{ 0, 0, 0, 0, 0},
			{ 0, 1, 1, 1, 0},
			{ 0, 1, 1, 1, 0},
			{ 1, 0, 1, 1, 0},
			{ 0, 0, 0, 0, 0}, };
	
	private static Integer mapLen = map.length-1;

	public static void main(String[] args) {

		BasicGridWorld gen = new BasicGridWorld(map,mapLen,mapLen); //0 index map is 11X11
		Domain domain = gen.generateDomain();

		State initialState = BasicGridWorld.getExampleState(domain);

		RewardFunction rf = new BasicRewardFunction(mapLen, mapLen); //Goal is at the top right grid
		TerminalFunction tf = new BasicTerminalFunction(mapLen, mapLen); //Goal is at the top right grid

		SimulatedEnvironment env = new SimulatedEnvironment(domain, rf, tf,
				initialState);
		//Print the map that is being analyzed
		System.out.println("/////Easy Grid World Analysis/////\n");
		MapPrinter.printMap(map);
		
		if (visualizeInitialGridWorld) {
			visualizeInitialGridWorld(domain, gen, env);
		}
		
		AnalysisRunner runner = new AnalysisRunner(MAX_ITERATIONS,NUM_INTERVALS);
		if(runValueIteration){
			runner.runValueIteration(gen,domain,initialState, rf, tf);
		}
		if(runPolicyIteration){
			runner.runPolicyIteration(gen,domain,initialState, rf, tf);
		}
		if(runQLearning){
			runner.runQLearning(gen,domain,initialState, rf, tf, env);
		}
		AnalysisAggregator.printAggregateAnalysis();
	}

	private static void visualizeInitialGridWorld(Domain domain,
			BasicGridWorld gen, SimulatedEnvironment env) {
		Visualizer v = gen.getVisualizer();
		VisualExplorer exp = new VisualExplorer(domain, env, v);

		exp.addKeyAction("w", BasicGridWorld.ACTIONNORTH);
		exp.addKeyAction("s", BasicGridWorld.ACTIONSOUTH);
		exp.addKeyAction("d", BasicGridWorld.ACTIONEAST);
		exp.addKeyAction("a", BasicGridWorld.ACTIONWEST);

		exp.initGUI();
	}
	
}