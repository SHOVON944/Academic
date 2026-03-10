} else{
                reverse(s, st_l, k-1);
                start_length = false;
            }
        }
        if(start_length){
            reverse(s, st_l, k-1);
        }
        return s;
    }
};