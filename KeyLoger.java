package KeyLoger;

import static java.lang.System.out;

import java.util.NoSuchElementException;

/** 
 * 201713074
 * 임예린
 */

import java.util.Scanner;

public class KeyLoger {

	public static void main(String[] args) {
		// 메인 함수. 테스트 케이스 개수만큼 스트링배열 생성, 키 입력받고 분석 함수 호출
		Scanner scan=new Scanner(System.in);
		out.print("테스트케이스의 개수를 입력하세요: ");
		int n=scan.nextInt();

		for(int i=0;i<n;i++) {
			out.println("테스트케이스 "+i+" 입력:");
			String s=new String(scan.next());
			insertDList(s,i);
		}
	}

	private static void insertDList(String s, int caseNum) {
		// 입력받은 스트링에서 비밀번호 추출
		DList key=new DList();
		DNode cursor=key.tail; //커서 첫 지정은 가장 뒤쪽.
		String keyarr[]=s.split("");
		
		for(int i=0;i<keyarr.length;i++) {
			if(keyarr[i].equals("<")) {
				if(!(cursor.getPrevious().getItem()==null)) { //범퍼
					cursor=cursor.getPrevious(); //한칸 앞으로
				}
			}
			else if(keyarr[i].equals(">")) {
				if(!(cursor.getItem()==null)) { //범퍼
					cursor=cursor.getNext();//한칸 뒤로
				}	
			}
			else if(keyarr[i].equals("-")) {
				if(!(cursor.getPrevious().getItem()==null)) { //범퍼
					key.delete(cursor.getPrevious());//커서 전에 있는 애를 삭제
				}			
			}
			else {
				key.insertBefore(cursor, keyarr[i]); //커서 앞쪽에 글자가 입력됨 커서 뒤로 물릴 필요 x
			}
		}
		
		//출력		
		cursor=key.head.getNext();
		
		out.println("테스트케이스 "+caseNum+" 출력:");
		for(int i=0;i<key.getSize();i++) {
			out.print(cursor.getItem());
			cursor=cursor.getNext();
		}
		out.println();		
	}
}


class DNode <E>{ //노드
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

class DList<E>{ //리스트
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
