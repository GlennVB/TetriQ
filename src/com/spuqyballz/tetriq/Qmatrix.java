package com.spuqyballz.tetriq;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Qmatrix extends JPanel {
	private int xSize;
	private int ySize;
	private int uSize=15;
	
	private int eEmpty = -1;
	
	private int matrix[][];
	
	/*
	 * Constructors
	 */

	public Qmatrix(int xSize, int ySize, int uSize, int[][] matrix) {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		this.uSize = uSize;
		this.matrix = matrix;
	}

	public Qmatrix(int xSize, int ySize) {
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		
		this.matrix = new int[xSize][ySize];
		this.setMatrix(this.eEmpty);
	}

	/*
	 * Getter & Setter methods
	 */
	
	public int getxSize() {
		return xSize;
	}

	public void setxSize(int xSize) {
		this.xSize = xSize;
	}

	public int getySize() {
		return ySize;
	}

	public void setySize(int ySize) {
		this.ySize = ySize;
	}

	public int getuSize() {
		return uSize;
	}

	public void setuSize(int bSize) {
		this.uSize = bSize;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	/*
	 * operations on elements of the matrix
	 */
	
	public boolean isInBounds(int x, int y){
		if(x>=0 && x<this.getxSize() && y>=0 && y<this.getySize()){
			return true;
		}
		return false;
	}
	
	public int getElement(int x, int y){
		if(this.isInBounds(x, y)){
			return this.matrix[x][y];
		}
		return -2;
	}
	
	public void setElement(int x, int y, int element){
		if(this.isInBounds(x, y)){
			this.matrix[x][y]=element;
		}
	}
	
	public void clearElement(int x, int y){
		if(this.isInBounds(x, y)){
			this.matrix[x][y]=this.eEmpty;
		}
	}
	
	public boolean isEmpty(int x, int y){
		if(this.isInBounds(x, y) && this.getElement(x, y)==this.eEmpty){
			return true;
		}
		return false;
	}
	
	/*
	 * matrix operations
	 */
	
	public int[][] rotateLeft(int m[][], int mxSize, int mySize){
		int mOut[][] = new int[mySize][mxSize];
		int i, j;

		// transpose matrix
		for(j=0; j<mySize; j++) {
			for (i=0; i<mxSize; i++) {
				mOut[j][i] = m[i][j];
			}
		}
		
		// mirror matrix
		
		return mOut;
	}
	
	public int[][] rotateRight(int m[][], int mxSize, int mySize){
		int mOut[][] = new int[mySize][mxSize];
		int i, j;
	    int tmp;

		// transpose matrix
		for(j=0; j<mySize; j++) {
			for (i=0; i<mxSize; i++) {
				mOut[j][i] = m[i][j];
			}
		}
		
		// mirror matrix
		for (j=0; j<mxSize; j++) {
			for (i=0; i<mySize; i++) {
					tmp = mOut[j][i];
					mOut[j][i]=mOut[mySize-j][i];
					mOut[mySize-j][i]=tmp;
				}
		}

		// transpose matrix
		for(j=0; j<mySize; j++) {
			for (i=0; i<mxSize; i++) {
				mOut[j][i] = m[i][j];
			}
		}
		
		return mOut;
	}
	
	public void clearMatrix(){
		int i,j;

		for(j=0; j<this.getySize(); j++){
			for (i=0; i<this.getxSize(); i++) {
				this.matrix[i][j]=this.eEmpty;
			}
		}
	}
	
	public void setMatrix(int value){
		int i,j;

		for(j=0; j<this.getySize(); j++){
			for (i=0; i<this.getxSize(); i++) {
				this.matrix[i][j]=value;
			}
		}
	}
	
	public void setMatrix(int[][] fill, int fxSize, int fySize){
		int i,j;

		for(j=0; j<this.getySize(); j++){
			for (i=0; i<this.getxSize(); i++) {
				if(i<fxSize && j<fySize){
					this.matrix[i][j]=fill[i][j];
				}
				else{
					this.matrix[i][j]=this.eEmpty;
				}
			}
		}
	}
	
	public void colorizeMatrix(int color){
		int i,j;

		for(j=0; j<this.getySize(); j++){
			for (i=0; i<this.getxSize(); i++) {
				if(this.getElement(i,j)!=this.eEmpty){
					this.matrix[i][j]=color;
				}
			}
		}
	}
	
	/*
	 * Visualization stuff
	 */
	

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int i, j;

		for (j = 0; j < this.getySize(); j++) {
			for (i = 0; i < this.getxSize(); i++) {
				if (this.getElement(i, j) != this.eEmpty) {
					switch (this.getElement(i, j)) {
					case 0:
						g.setColor(Color.blue);
						break;
					case 1:
						g.setColor(Color.red);
						break;
					case 2:
						g.setColor(Color.orange);
						break;
					case 3:
						g.setColor(Color.yellow);
						break;
					case 4:
						g.setColor(Color.green);
						break;
					case 5:
						g.setColor(Color.MAGENTA);
						break;
					case 6:
						g.setColor(Color.pink);
						break;
					case 7:
						g.setColor(Color.white);
						break;
					case -1:
						g.setColor(Color.black);
						break;
					}
					g.fillRect(i*this.uSize, j*this.uSize, this.uSize, this.uSize);
					g.setColor(Color.DARK_GRAY);
					g.drawRect(i*this.uSize, j*this.uSize, this.uSize, this.uSize);
				}
			}
		}
	}

	public int geteEmpty() {
		return eEmpty;
	}

	public void seteEmpty(int eEmpty) {
		this.eEmpty = eEmpty;
	}
	
}