    Branch *leftBranch = root;
    int index = 0;
    while(leftBranch){
        cout << "left branch n." << index++ << " is " << leftBranch->data << endl;
        leftBranch = leftBranch->left;
    }
    Branch *rightBranch = root;
    index = 0;
    while(rightBranch){
        cout << "right branch n." << index++ << " is " << rightBranch->data << endl;
        rightBranch = rightBranch->right;
    }