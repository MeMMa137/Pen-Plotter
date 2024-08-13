class Protocol:
    def __init__(self):
        self.points_separator=";"
        self.coords_separator=","
        self.args_separator="_"
        self.n_digit_length = 4

    def _str_fixed_len(self, value):
        return '0'*(self.n_digit_length-value) + str(value)
            
    def encode(self, p):
        s = self._str_fixed_len( len(p) )
        s += self.args_separator
        s += self.points_separator.join(map(lambda e: str(e.x)+self.coords_separator+str(e.y), p))
        return s