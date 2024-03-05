[org 0x0100]

jmp start

clearscreen:
push bp
mov bp,sp
push ax
push di
push cx

mov ax,0xB800
mov es,ax
mov di,0
mov ax,0x0720

nextloc:
mov word[es:di],ax
add di,2
cmp di,4000
jne nextloc

;call concrete
;call lava
 
pop cx
pop di
pop ax
pop bp

ret 
;;;;;;;BG
bG:
push bp
mov bp,sp
push ax
push di
push cx

mov ax,0xB800
mov es,ax
mov di,0
mov ax,0x0bdb

nextloc1:
mov word[es:di],ax
add di,2
cmp di,4000
jne nextloc1

call concrete
call lava
 call clouds
 call bucket
pop cx
pop di
pop ax
pop bp

ret 

;;;;;;;;;;;;;;time&score
 time1:
 push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
 
  push ds
  pop es
  mov di,[bp+4]
  mov cx,0xffff
  mov al,0
  repne scasb 
  mov ax,0xffff
  sub ax,cx
  dec ax
  
  mov cx,ax
  mov ax,0xB800
  mov es,ax
  mov ax,[bp+6]
  mov di,ax
  mov ah,[bp+8]
  mov si,[bp+4]
 
            cld
  nextchar:
         lodsb
         stosw
         loop nextchar
 
 
  pop si
  pop cx
  pop di
  pop ax
  pop bp
  ret 6
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;boundary
boundary:
push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
 

mov ax,0xB800
mov es,ax
mov ah,0x60
mov al,0xc9
mov di,494
mov word[es:di],ax
add di,2
mov al,0xcd


nextl1:
mov word[es:di],ax
add di,2
cmp di,626
jne nextl1

mov di,654
mov ah,0x60
;2a
mov al,0xba
nextl12:
mov word[es:di],ax
add di,160
cmp di,3694
jne nextl12
;;;;;;;;3666

mov al,0xc8
mov word[es:di],ax
add di,2
mov al,0xcd

nextl13:
mov word[es:di],ax
add di,2
cmp di,3826
jne nextl13

mov al,0xbc
mov word[es:di],ax
sub di,160
mov al,0xba

nextl14:
mov word[es:di],ax
sub di,160
cmp di,626
jne nextl14


mov al,0xbb
mov word[es:di],ax

pop si
  pop cx
  pop di
  pop ax
  pop bp
ret
;;;;;;;;;;;;;;;;;;;;lava
;;3680.red start hoga
lava:
  push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  
  mov ax,0xB800
  mov es,ax
  
 
  
  mov di,3680
  mov ax,0x46dc
  
  nextred:
  mov word[es:di],ax
  add di,4
  cmp di, 3840
  jne nextred
  
  mov di,3682
  mov ax,0x64dc
  
  nextred1:
  mov word[es:di],ax
  add di,4
  cmp di, 3842
  jne nextred1
  
   pop si
  pop cx
  pop di
  pop ax
  pop bp
  ret
  
  ;;;;;;;;;;;;;;;;;;;;;object printing
 printobj:
 push bp
 mov bp,sp
 push ax
 push di
 push cx

 mov ax,0xB800
 mov es,ax
 mov di,[bp+6]
 mov ah,[bp+4];0x60
 mov al,0xdb;0x07 se dots arahay.brown plus


 mov word[es:di],ax
  add di,158
  mov word[es:di],ax
  add di,2
  mov al,0xb1
   mov word[es:di],ax
   mov al,0xdb
  add di,2
   mov word[es:di],ax
  
  add di,158
  mov word[es:di],ax
  
 
 pop cx
 pop di
 pop ax
 pop bp

 ret 4
  
  
  ;;;;;;;;;;;;;;;;;;;;clouds

clouds:
push ax
push es
push di
push si
push cx
push dx

mov ax,0xB800
mov es,ax
mov di,160
 mov ax,0x0Fdb
 
next1:;start 160
mov word[es:di],ax
add di,2
cmp di,172
jne next1
mov di,250

next2:;start 220
mov word[es:di],ax
add di,2
cmp di,300
jne next2


mov di,320
next3:;start 160
mov word[es:di],ax
add di,2
cmp di,332
jne next3

mov di,356
next4:;start 160
mov word[es:di],ax
add di,2
cmp di,360
jne next4

mov di,440
next5:;start 160
mov word[es:di],ax
add di,2
cmp di,450
jne next5

mov di,480
next6:;start 480
mov word[es:di],ax
add di,2
cmp di,540
jne next6

mov di,650
next7:;start 480
mov word[es:di],ax
add di,2
cmp di,670
jne next7

mov di,680
next8:;start 480
mov word[es:di],ax
add di,2
cmp di,684
jne next8

mov di,716
next81:;start 480
mov word[es:di],ax
add di,2
cmp di,724
jne next81



mov di,752
next9:;start 480
mov word[es:di],ax
add di,2
cmp di,756
jne next9

mov di,820
next10:;start 480
mov word[es:di],ax
add di,2
cmp di,824
jne next10

mov di,912
next11:;start 480
mov word[es:di],ax
add di,2
cmp di,916
jne next11


mov di,1012
next12:;start 480
mov word[es:di],ax
add di,2
cmp di,1018
jne next12
pop dx
pop cx
pop si
pop di
pop es
pop ax

ret
  
;;;;;;;;;conceret
concrete:;;line 22 23 24
  push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  
  mov ax,0xB800
  mov es,ax
  
  mov di,3840
  mov ax,0x7020
 nextgrey:
  mov word[es:di],ax
  add di,4
  cmp di, 4000
  jne nextgrey

 mov di,3842
  mov ax,0x8020
 nextdgrey:
  mov word[es:di],ax
  add di,4
  cmp di, 3998
  ;cmp di, 3998
  jne nextdgrey

  pop si
  pop cx
  pop di
  pop ax
  pop bp
  ret
  
;;;;;;;;;;;bucket
  bucket:   ;;line 19 start
  push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  push ds
  
  
  mov ax,0xB800
  mov es,ax
  
  mov di,word[cs:bucketcordinate];;;;3280;;;;;;middlepart
  mov ax,0x8020
  mov word[es:di],ax
 add di,6
  mov word[es:di],ax
  
  add di,154
  mov cx,di
  mov ax,0x7020
  mov word[es:di],ax
  add di,2
  mov word[es:di],ax
  add di,2
  mov word[es:di],ax
    add di,2
  mov word[es:di],ax
  
  mov ax,0x70
push ax
push cx
mov ax,bucket1
push ax
call time1
     
  pop ds
  pop si
  pop cx
  pop di
  pop ax
  pop bp
  ret
 ;;;;;;;;;;;;;;;BOMB
 
bomb:

   push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  
  mov ax,0xB800
  mov es,ax
  mov di,[bp+4]
  
   mov ax,0x04dd
  mov word[es:di],ax
  add di,4
  mov ax,0x04de
  mov word[es:di],ax
  
  mov di,[bp+4]
  add di,2
  mov ah,0x40;
  mov al,0x20
  mov word[es:di],ax
  add di,158
  mov cx,di
  mov word[es:di],ax
  add di,2
mov word[es:di],ax
add di,2
mov word[es:di],ax

mov ax,0x40
push ax
push cx
mov ax,bomb1
push ax
call time1
  
  pop si
  pop cx
  pop di
  pop ax
  pop bp
  
  ret 2
 ;;;;;;;;;;;;;;;;;;;;;;;;;;;randomgenerator


randomnumbergene:

push bp 
push cx
push dx
push ax
push bx
mov cx,0
mov ax,0
rdtsc 
xor dx,dx
mov cx,152
div cx
mov word[cs:randomNum],dx 


mov dx,0
mov ax,word[cs:randomNum]
mov cx ,2
div cx
cmp dx,0
je final

mov ax,0
mov ax,word[cs:randomNum]
add ax,1
mov word[randomNum],ax 
final:


mov ax,0
pop bx
pop ax
pop dx
pop cx
pop bp
ret
  
  
  
  
  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;delayyyyyy
   delayfunc:
   push bx
   push cx
mov bx,5
delay:
mov cx,0xFFFF
l18:
mov ax,1
add ax,1
loop l18

dec bx
cmp bx,0
jne delay
pop cx
pop bx
ret
  
  
     delayfunc2:
   push bx
   push cx
mov bx,7
delay2:
mov cx,0xFFFF
l181:
mov ax,1
add ax,1
loop l181

dec bx
cmp bx,0
jne delay2
pop cx
pop bx
ret



delayfunc2sec:
push bx
push cx
mov bx,2
delay24:
mov cx,0xFFFF
l1814:
mov ax,1
add ax,1
loop l1814

dec bx
cmp bx,0
jne delay24
pop cx
pop bx
ret




delayfunc890:
mov bx,35
delay567:
mov cx,0xFFFF
l1:
mov ax,1
add ax,1
loop l1

dec bx
cmp bx,0
jne delay567

ret





  
  


kbsir:
push ax
push es
push ds

mov ax,0xb800
mov es,ax
mov ds,ax

in al,0x60
cmp al,0xcd
je shiftright
jne nextcmp

nextcmp:

cmp al,0xcb
je shiftleft
jne nomatch
;;;;;;;;;;;;;;;;;;;;;shiftright
shiftright:
mov si,word[cs:bucketcordinate]
add si,6
mov di,si
add di,2
cmp di,3360
je nomatch
mov cx,6
std
rep movsw

add di,6

mov ax,0x2020
;mov word[es:di],ax

mov word[cs:bucketcordinate],di
add si,6
add si,160
mov ax,0x6020
add si,6
mov di,si
add di ,2
mov cx,6
std
rep movsw
jmp nomatch


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;shiftleft
shiftleft:
mov si,word[cs:bucketcordinate]
mov di,si
sub di ,2
cmp di,3198
je nomatch
mov word[cs:bucketcordinate],di
mov cx,6
cld
rep movsw
mov si,word[cs:bucketcordinate]
add si,160
mov di,si
sub di ,2
mov cx,8
cld
rep movsw
jmp nomatch
;;;;;;;shiftleft
nomatch:
mov al,0x20
out 0x20,al
  pop ds
  pop es
  pop ax
 ;;; jmp far[cs:oldisr]
 iret
 
 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;print number
  printscore: 
push bp
mov bp, sp
push es
push ax
push bx
push cx
push dx
push di

mov ax, 0xb800
mov es, ax ; point es to video base
mov ax, [bp+4]
mov dx,0; load number in ax
mov bx, 10 ; use base 10 for division
mov cx, 0 ; initialize count of digits
nextdigit: mov dx, 0 ; zero upper half of dividend
div bx ; divide by 10
add dl, 0x30 ; convert digit into ascii value
push dx ; save ascii value on stack
inc cx ; increment count of values
cmp ax, 0 ; is the quotient zero
jnz nextdigit ; if no divide it again
mov di,[bp+6] ; point di to 70th column
nextpos: pop dx ; remove a digit from the stack
mov dh,[bp+8] ; use normal attribute
mov [es:di], dx ; print char on screen
add di, 2 ; move to next screen location
loop nextpos ; repeat for all digits on stack
pop di
pop dx
pop cx
pop bx
pop ax

pop es
pop bp
ret 6
;;;;;;;;;;;;;;;;;;;;;;;;;;;colbomb
collisionbomb:

push bp
mov bp, sp
push es
push ax
push bx
push cx
push dx
push di
push si
push ds


mov ax, 0xb800
mov es, ax 
mov ds, ax 
mov di,word[cs:bucketcordinate]
add di,2
mov si,[bp+4]
add si,320
cmp di,si
je clearobject1234
jne nomatch564
clearobject1234:
mov si,word[cs:bombcheck]
mov si,1
mov word[cs:bombcheck],si
;jmp far[cs:ending]
 nomatch564:  
 
 
pop ds
pop si
pop di
pop dx
pop cx
pop bx
pop ax
pop es
pop bp
ret 2


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;collision
collision:
push bp
mov bp, sp
push es
push ax
push bx
push cx
push dx
push di
push si
push ds

mov ax, 0xb800
mov es, ax 
mov ds, ax 
mov di,word[cs:bucketcordinate]
add di,2
add di,160;;;;;;;;;;;;;;;;;;;;;changes
mov bx,di
add bx,2
mov si,[bp+4]
add si,480
cmp di,si
je clearobject123
cmp bx,si
je clearobject123
jne nomatch5
clearobject123:
       mov si,[bp+4]
       lodsw
       cmp ax,0x09db
       jne nextcolour
  mov ax,[bp+4]
  push ax
  mov ax,0x0bdb
  push ax
  call clarobject
   mov dx,0
   mov dx,word[cs:score]
   add dx,5
   mov word[cs:score],dx
   mov dx,0
mov dx,word[cs:obj1cor]
	add dx,480
	mov word[cs:obj1cor],dx
   jmp nextcolour
   nomatch5:
   jmp nomatch56
   
   
   nextcolour:
   cmp ax,0x0cdb
   jne nextcolour1
   
   mov ax,[bp+4]
  push ax
  mov ax,0x0bdb
  push ax
  call clarobject
   mov dx,0
   mov dx,word[cs:score]
   add dx,10
   mov word[cs:score],dx
   mov dx,0
 mov dx,word[cs:obj3cor]
	add dx,480
	mov word[cs:obj3cor],dx
   nextcolour1:
      cmp ax,0x0edb
   jne nomatch56
   
    mov ax,[bp+4]
    push ax
    mov ax,0x0bdb
    push ax
    call clarobject
    mov dx,0
    mov dx,word[cs:score]
    add dx,15
    mov word[cs:score],dx
	mov dx,word[cs:obj2cor]
	add dx,480
	mov word[cs:obj2cor],dx
   
 nomatch56:  
pop ds
pop si
pop di
pop dx
pop cx
pop bx
pop ax
pop es
pop bp
ret 2

;;;;;;;;;;;;;;;;;;;;;;;nextobjs

collision2:
push bp
mov bp, sp
push es
push ax
push bx
push cx
push dx
push di
push si
push ds

mov ax, 0xb800
mov es, ax 
mov ds, ax 
mov di,word[cs:bucketcordinate]
add di,2
add di,160;;;;;;;;;;;;;;;;;;;;;changes
mov bx,di
add bx,2
mov si,[bp+4]
add si,480
cmp di,si
je clearobject12345
cmp bx,si
je clearobject12345
jne nomatch58
clearobject12345:
       mov si,[bp+4]
       lodsw
       cmp ax,0x09db
       jne nextcolour13
  mov ax,[bp+4]
  push ax
  mov ax,0x0bdb
  push ax
  call clarobject
   mov dx,0
   mov dx,word[cs:score]
   add dx,5
   mov word[cs:score],dx
   mov dx,0
mov dx,word[cs:obj11cor]
	add dx,480
	mov word[cs:obj11cor],dx
	
   jmp nextcolour13  
   nomatch58:
   jmp nomatch561
   
   
  nextcolour13:
   cmp ax,0x0cdb
   jne nextcolour2
   
   mov ax,[bp+4]
  push ax
  mov ax,0x0bdb
  push ax
  call clarobject
   mov dx,0
   mov dx,word[cs:score]
   add dx,10
   mov word[cs:score],dx
   mov dx,0
 mov dx,word[cs:obj33cor]
	add dx,480
	mov word[cs:obj33cor],dx
   
   
   nextcolour2:
      cmp ax,0x0edb
   jne nomatch56
   
    mov ax,[bp+4]
    push ax
    mov ax,0x0bdb
    push ax
    call clarobject
    mov dx,0
    mov dx,word[cs:score]
    add dx,15
    mov word[cs:score],dx
	mov dx,word[cs:obj22cor]
	add dx,480
	mov word[cs:obj22cor],dx
   
 nomatch561:  

pop ds
pop si
pop di
pop dx
pop cx
pop bx
pop ax

pop es
pop bp

ret 2

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;objclear
 clarobject:
 push bp
 mov bp,sp
 push ax
 push di
 push cx

 mov ax,0xB800
 mov es,ax
 mov di,[bp+6]
 mov ax,[bp+4];0x60
 ;;mov al,0xdb;0x07 se dots arahay.brown plus
 mov word[es:di],ax
  add di,158
  mov word[es:di],ax
  add di,2
  mov al,0xdb
   mov word[es:di],ax
   mov al,0xdb
  add di,2
   mov word[es:di],ax
  
  add di,158
  mov word[es:di],ax
 pop cx
 pop di
 pop ax
 pop bp
 ret 4
 ;;;;;;;;;;;;;;;;;;;;;;;prtiny number
 
 printnum: push bp
mov bp, sp
push es
push ax
push bx
push cx
push dx
push di
mov ax, 0xb800
mov es, ax ; point es to video base
mov ax, [bp+4] ; load number in ax
mov bx, 10 ; use base 10 for division
mov cx, 0 ; initialize count of digits
nextdigit5: mov dx, 0 ; zero upper half of dividend
div bx ; divide by 10
add dl, 0x30 ; convert digit into ascii value
push dx ; save ascii value on stack
inc cx ; increment count of values
cmp ax, 0 ; is the quotient zero
jnz nextdigit5 ; if no divide it again
mov di, 790 ; point di to 70th column
nextpos5: pop dx ; remove a digit from the stack
mov dh, 0x07 ; use normal attribute
mov [es:di], dx ; print char on screen
add di, 2 ; move to next screen location
loop nextpos5 ; repeat for all digits on stack
pop di
pop dx
pop cx
pop bx
pop ax
 pop es
pop bp
ret 2
  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
 ;;;;; timer:
  timer: 
push ax
push bx
push dx
mov dx,word [cs:timecountf]
inc dx
mov word [cs:timecountf],dx
dec word[cs:tickcountf]
cmp word[tickcountf],0
jne mactno
mov word[cs:tickcountf],20
cmp word[cs:t],120
ja mactno
inc word[cs:t]

mactno:

mov al,0x20
out 0x20,al
pop dx
pop bx
pop ax
iret
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;BELOW IS WELCOME PAGE DISPLAY FUNCTIONS
  bucket6:   ;;line 19 start
  push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  
  mov ax,0xB800
  mov es,ax
  
  
  mov di,1320;;middlepart
  mov ax,0x8020
 ; nextb:
  mov word[es:di],ax
 add di,6
  mov word[es:di],ax
  
  add di,154
  mov cx,di
  mov ax,0x7020
  mov word[es:di],ax
  add di,2
  mov word[es:di],ax
  add di,2
  mov word[es:di],ax
    add di,2
  mov word[es:di],ax
  
  mov ax,0x70
push ax
push cx
mov ax,bucket1
push ax
call time1
  
  pop si
  pop cx
  pop di
  pop ax
  pop bp
  ret


;;;;;;;;;;;;;;;instrunctionpage
instructions:

push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
  
  call boundary123

;;obj1
mov ax,0x06
push ax
mov ax,818
push ax
mov ax,instruction1
push ax
call time1

  mov ax,680
  push ax;;position
  mov ax,0x0e
  push ax;;attribute
  call printobj

;;;obj2
mov ax,0x06
push ax
mov ax,852
push ax
mov ax,instruction2
push ax
call time1

 mov ax,714
  push ax;;position
  mov ax,0x0c
  push ax;;attribute
  call printobj

;;obj3
mov ax,0x06
push ax
mov ax,886
push ax
mov ax,instruction3
push ax
call time1

mov ax,748
  push ax;;position
  mov ax,0x09
  push ax;;attribute
  call printobj

;;bombb
mov ax,0x06
push ax
mov ax,918
push ax
mov ax,instruction5
push ax
call time1

mov ax,934
  push ax
  call bomb
 
;;;;;;;;;bucket
mov ax,0x06
push ax
mov ax,1300
push ax
mov ax,instruction4
push ax
call time1
call bucket6
;;;;;instructionsdisplay

mov ax,0x06
push ax
mov ax,1778
push ax
mov ax,lolzno
push ax
call time1

mov ax,0x06
push ax
mov ax,1938
push ax
mov ax,ins0
push ax
call time1

mov ax,0x06
push ax
mov ax,2098
push ax
mov ax,ins7
push ax
call time1

mov ax,0x06
push ax
mov ax,2258
push ax
mov ax,ins1
push ax
call time1

mov ax,0x06
push ax
mov ax,2578
push ax
mov ax,ins2
push ax
call time1

mov ax,0x06
push ax
mov ax,2738
push ax
mov ax,ins3
push ax
call time1

mov ax,0x06
push ax
mov ax,2898
push ax
mov ax,ins4
push ax
call time1

mov ax,0x06
push ax
mov ax,3058
push ax
mov ax,ins5
push ax
call time1

mov ax,0x06
push ax
mov ax,3218
push ax
mov ax,ins6
push ax
call time1

mov ax,0x06
push ax
mov ax,3538
push ax
mov ax,instruc
push ax
call time1

pop si
  pop cx
  pop di
  pop ax
  pop bp
ret

welcomescreen:
 mov ax , 0x07
push ax
  mov ax,1650;;row 10 column 25
  push ax
  mov ax,welcome
  push ax
call time1

mov ax , 0x07
push ax
 mov ax,1978
  push ax
  mov ax,names
  push ax 
call time1

call delayfunc890
call clearscreen1
call instructions
call delayfunc890
ret

clearscreen1:
push bp
mov bp,sp
push ax
push di
push cx

mov ax,0xB800
mov es,ax
mov di,0
;mov ax,0x0720
mov ah,0x06
mov al,0xdb
nextloc198:
mov word[es:di],ax
add di,2
cmp di,4000
jne nextloc198

pop cx
pop di
pop ax
pop bp

ret
;;;;;;;;;;;;;;;;boundary
boundary123:
push bp
  mov bp,sp
  push ax
  push di
  push cx
  push si
 
mov ax,0xB800
mov es,ax
mov ah,0x60
mov al,0xc9
mov di,494
mov word[es:di],ax
add di,2
mov al,0xcd

nextl341:
mov word[es:di],ax
add di,2
cmp di,626
jne nextl341

mov di,654
mov ah,0x60
;2a
mov al,0xba
nextl1342:
mov word[es:di],ax
add di,160
cmp di,3694
jne nextl1342

mov al,0xc8
mov word[es:di],ax
add di,2
mov al,0xcd

nextl1343:
mov word[es:di],ax
add di,2
cmp di,3826
jne nextl1343

mov al,0xbc
mov word[es:di],ax
sub di,160
mov al,0xba

nextl1344:
mov word[es:di],ax
sub di,160
cmp di,626
jne nextl1344
mov al,0xbb
mov word[es:di],ax
pop si
  pop cx
  pop di
  pop ax
  pop bp
ret
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;BELOW IS ENDING SCREEN

endingscreen:
push ax
push bx
push si
push di
push cx
push dx

  mov ax,0xbc
push ax
mov ax,1352;;;;;;;i520 if above printing
push ax
  mov ax,gameover
 push ax 
call time1
;;;;call delayfunc


mov ax,0xB800
mov es,ax
mov ah,0x60
mov al,0xc9
mov di,1660
mov word[es:di],ax
add di,2
mov al,0xcd

nextl3415:
mov word[es:di],ax
add di,2
cmp di,1700
jne nextl3415

mov ax,0x06db
mov di,1822
inner1line:
mov word[es:di],ax
add di,2
cmp di,1860
jne inner1line

mov ax,0x06db
mov di,1982
inner2line:
mov word[es:di],ax
add di,2
cmp di,2020
jne inner2line

mov ax,0x06db
mov di,2142

inner3line:
mov word[es:di],ax
add di,2
cmp di,2180
jne inner3line

mov ax,0x06db
mov di,2302
inner4line:
mov word[es:di],ax
add di,2
cmp di,2340
jne inner4line

mov di,1820
mov ah,0x60
mov al,0xba

nextl13429:
mov word[es:di],ax
add di,160
cmp di,2460
jne nextl13429
;;;;;;;;3666

mov al,0xc8
mov word[es:di],ax
add di,2
mov al,0xcd

nextl13431:
mov word[es:di],ax
add di,2
cmp di,2500
jne nextl13431

mov al,0xbc
mov word[es:di],ax
sub di,160
mov al,0xba

nextl13441:
mov word[es:di],ax
sub di,160
cmp di,1700
jne nextl13441
mov al,0xbb
mov word[es:di],ax


mov ax,0x06
push ax
mov ax,1992
push ax
mov ax,chara
push ax
call time1

mov ax,0x06
  push ax
   mov ax,2006
 push ax
  mov ax,word[cs:score]
  push ax
 call printscore

mov ax,0x06
push ax
mov ax,2152
push ax
mov ax,char1
push ax
call time1

mov ax,0x06
  push ax
   mov ax,2166
 push ax
  mov ax,word[cs:t]
  push ax
 call printscore


pop dx
pop cx
pop di
pop si
pop bx
pop ax
ret


  ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;start
  start:
  call clearscreen
 call welcomescreen

 lo: 
  mov ah,0
  int 0x16
  
  cmp al,32
  jne  lo
 call bG

 starting:
 xor ax, ax
mov es, ax 
cli 
mov word [es:8*4], timer
mov [es:8*4+2], cs 
sti 

 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;objectmoving
 call bucket
 xor ax, ax
mov es, ax ; point es to IVT base
mov ax, [es:9*4]
mov [oldisr], ax ; save offset of old routine
mov ax, [es:9*4+2]
mov [oldisr+2], ax

   xor ax,ax
  mov es,ax
  mov ax,[es:9*4]
  mov [oldisr],ax
  mov ax,[es:9*4+2]
  mov [oldisr+2],ax 
  cli
  mov word[es:9*4],kbsir
  mov [es:9*4+2],cs
  sti
 
 
  

  
 
 
 loop1:
 mov word[cs:score],0
 mov word[cs:obj1cor],-666
 
mov word[cs:obj2cor],-2220

 mov word[cs:obj3cor],-1000
 mov word[cs:tntcor],-204
 
 mov word[cs:obj11cor],-3000
 
mov word[cs:obj33cor],-1500
 
 mov word[cs:tnt2cor],-770
 
 
 repeatloop:
 call bG


 mov ax,0x35
  push ax
    mov ax,300
  push ax;;position
  mov ax,chara
  push ax
  call time1
  
  mov ax,0x35
  push ax
   mov ax,314
 push ax
  mov ax,word[cs:score]
  push ax
 call printscore
 

  mov ax,0x35
  push ax
    mov ax,460
  push ax;;position
  mov ax,char1
  push ax
  call time1
 
 mov ax,0x35
  push ax
   mov ax,474
 push ax
  mov ax,word[cs:t]
  push ax
 call printscore
 
 mov ax,0
  
 
   nahi45:
   next5678: 
 
  mov ax,0
  mov ax,word[cs:obj1cor]
  push ax
  mov ax,0x09
  push ax;;attribute
  call printobj
  
 
 mov ax,word[cs:obj2cor]
  push ax
  mov ax,0x0e
  push ax;;attribute
  call printobj
  
   mov ax,word[cs:obj3cor]
  push ax
  mov ax,0x0c
  push ax;;attribute
  call printobj
  
   mov ax,word[cs:tntcor]
  push ax
  call bomb
  
  ;;;;;;;;;;;;;;;;;;;;;;;dosrayobjectsprinting
  
  mov ax,0
  mov ax,word[cs:obj11cor]
  push ax
  mov ax,0x09
  push ax;;attribute
  call printobj
  
 
 mov ax,word[cs:obj33cor]
  push ax
  mov ax,0x0c
  push ax;;attribute
  call printobj
  
   mov ax,word[cs:tnt2cor]
  push ax
  call bomb
   
   call delayfunc
 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;checking collisions
 
;;;;;;;;;  mov si,word[cs:index1]
  mov ax,word[cs:obj1cor]
 push ax
 call collision
 
;;;;; call delayfunc2sec
 
 mov ax,word[cs:obj2cor]
 push ax
 call collision
 
  mov ax,word[cs:obj3cor]
  push ax
 call collision  
 mov ax,word[cs:obj11cor]
 push ax
 call collision2
 
  mov ax,word[cs:obj33cor]
 push ax
 call collision2 
  
  mov ax,word[cs:tnt2cor]
  push ax
  call collisionbomb
 nooo:
 mov ax,word[cs:tntcor]
  push ax
  call collisionbomb
  
  mov si,0
 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;incrementing in corrdinate and storing new cordinate in checks
 nahi:
 
 add word[cs:obj1cor],160 
 add word[cs:obj2cor],160 
add word[cs:obj3cor],160 
add word[cs:tntcor],160 
 add word[cs:obj11cor],160 
 add word[cs:obj33cor],160 
add word[cs:tnt2cor],160 
 
 cmp word[cs:obj1cor],3200
 jng next22
 one:
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:obj1cor],dx
 mov ax,0
 
 next22:
 cmp word[cs:obj2cor],3200
 jng next23
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:obj2cor],dx
 mov ax,0

  next23:
 cmp word[cs:obj3cor],3200
 jng next24
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:obj3cor],dx
 mov ax,0
 
 next24:
 cmp word[cs:tntcor],3200
 jng next111
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:tntcor],dx
 mov ax,0
 
 
 
 next111:
 cmp word[cs:obj11cor],3200
 jng next222
 one1:
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:obj11cor],dx
 mov ax,0
 
 next222:
 cmp word[cs:obj33cor],3200
 jng next244
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:obj33cor],dx
 mov ax,0

 next244:
 cmp word[cs:tnt2cor],3200
 jng matchno
 mov word[cs:randomNum],0
 call randomnumbergene
 mov dx,0
 mov dx,word[cs:randomNum]
 mov word[cs:tnt2cor],dx
 mov ax,0
 
 matchno:
  mov si,0
  mov si,word[cs:bombcheck]
  cmp si,1
 
 je ending

 cmp word[cs:t],120
 jne repeatloop

mov ax, [oldisr] ; read old offset in ax
mov bx, [oldisr+2] ; read old segment in bx
cli ; disable interrupts
mov [es:9*4], ax ; restore old offset from ax
mov [es:9*4+2], bx ; restore old segment from bx
sti

 ending:
 ;mov word[cs:t],120
 

call endingscreen

mov ax,0x4c00
int 21h
oldisr dw 0
score dw 0
timepri dw 0
loc: dw 880
bucket1: db 'bkt',0
bomb1 db 'TNT',0
checkobj1 dw 0
checkobj2 dw 0
checkobj3 dw 0
checkTNT dw 0
check1 dw 0
bucketcordinate dw 3280
timecountf dw 0
tickcountf dw 20
index1 dw 0
index2 dw 0
index3 dw 0
indextnt dw 0
bombcheck dw 0
randomNum dw 0
obj1cor dw 0
obj2cor dw 0
obj3cor dw 0
tntcor dw 0
attri1 db 0x09
sec: dw 0
t dw 0
chara db 'score=',0 
char1 db 'Time=',0
obj11cor dw 0
obj22cor dw 0
obj33cor dw 0
tnt2cor dw 0
welcome db 'WELCOME TO CATCH & RELEASE ',0 
names db 'BY NAGEEN AND FILZA',0
instruction1 db 'OBJECT1:',0
instruction2 db 'OBJECT2:',0
instruction3 db 'OBJECT3:',0
instruction4 db 'BUCKET:',0
instruction5 db 'BOMB:',0
lolzno db 'PLAYING INSTRUCTIONS:',0
ins0 db 'COLLECT OBJECTS TO GAIN SCORE AND AVOID BOMB',0
ins1 db 'SCORE FORMAT:',0
ins2 db 'OBJECT1 CARRIES 15 SCORE',0
ins3 db 'OBJECT2 CARRIES 10 SCORE',0
ins4 db 'OBJECT3 CARRIES 05 SCORE',0
ins5 db 'USE -> FOR MOVING  BUCKET TO RIGHT',0
ins6 db 'USE <- FOR MOVING  BUCKET TO RIGHT',0
ins7 db 'TOTAL TIME LIMIT: 120 seconds',0
instruc db 'PRESS SPACE TO CONTINUE',0
endprint db 'Press Space to play again',0
gameover db 'GAME OVER',0