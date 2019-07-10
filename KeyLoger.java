package KeyLoger;

import static java.lang.System.out;

import java.util.NoSuchElementException;

/** 
 * 201713074
 * �ӿ���
 */

import java.util.Scanner;

public class KeyLoger {

	public static void main(String[] args) {
		// ���� �Լ�. �׽�Ʈ ���̽� ������ŭ ��Ʈ���迭 ����, Ű �Է¹ް� �м� �Լ� ȣ��
		Scanner scan=new Scanner(System.in);
		out.print("�׽�Ʈ���̽��� ������ �Է��ϼ���: ");
		int n=scan.nextInt();

		for(int i=0;i<n;i++) {
			out.println("�׽�Ʈ���̽� "+i+" �Է�:");
			String s=new String(scan.next());
			insertDList(s,i);
		}
	}

	private static void insertDList(String s, int caseNum) {
		// �Է¹��� ��Ʈ������ ��й�ȣ ����
		DList key=new DList();
		DNode cursor=key.tail; //Ŀ�� ù ������ ���� ����.
		String keyarr[]=s.split("");
		
		for(int i=0;i<keyarr.length;i++) {
			if(keyarr[i].equals("<")) {
				if(!(cursor.getPrevious().getItem()==null)) { //����
					cursor=cursor.getPrevious(); //��ĭ ������
				}
			}
			else if(keyarr[i].equals(">")) {
				if(!(cursor.getItem()==null)) { //����
					cursor=cursor.getNext();//��ĭ �ڷ�
				}	
			}
			else if(keyarr[i].equals("-")) {
				if(!(cursor.getPrevious().getItem()==null)) { //����
					key.delete(cursor.getPrevious());//Ŀ�� ���� �ִ� �ָ� ����
				}			
			}
			else {
				key.insertBefore(cursor, keyarr[i]); //Ŀ�� ���ʿ� ���ڰ� �Էµ� Ŀ�� �ڷ� ���� �ʿ� x
			}
		}
		
		//���		
		cursor=key.head.getNext();
		
		out.println("�׽�Ʈ���̽� "+caseNum+" ���:");
		for(int i=0;i<key.getSize();i++) {
			out.print(cursor.getItem());
			cursor=cursor.getNext();
		}
		out.println();		
	}
}


class DNode <E>{ //���
	private E item;
	private DNode previous;
	private DNode next;
	
	public DNode (E newItem, DNode p, DNode q) {
		item=newItem;
		previous=p;
		next=q;
	}
	
	public E getItem() { return item; } 
	public DNode getPrevious() { return previous; }
	public DNode getNext() { return next; }
	
	public void setItem(E newItem) { item=newItem; } 
	public void setPrevious(DNode p) { previous=p; }
	public void setNext(DNode q) { next=q; }
}

class DList<E>{ //����Ʈ
	protected DNode head,tail;
	protected int size;

	public DList() {
		head=new DNode(null, null, null);
		tail=new DNode(null, head, null);
		head.setNext(tail);
		size=0;
	}
	
	public int getSize() { return size; }
	
	public void insertBefore(DNode p,E newItem) {
		DNode t=p.getPrevious();
		DNode newNode=new DNode(newItem,t,p);
		p.setPrevious(newNode);
		t.setNext(newNode);
		size++;
	}
	
	public void delete(DNode x) {
		if(x==null) throw new NoSuchElementException();
		
		DNode f=x.getPrevious();
		DNode r=x.getNext();
		
		f.setNext(r);
		r.setPrevious(f);
		size--;
	}
}
