package com.example.cadastropessoas.domain.endereco.entity;

public enum UF {
  AC {
    @Override
    public String getDescricao() {
      return "Acre";
    }
  },
  AL {
    @Override
    public String getDescricao() {
      return "Alagoas";
    }
  },
  AP {
    @Override
    public String getDescricao() {
      return "Amapá";
    }
  },
  AM {
    @Override
    public String getDescricao() {
      return "Amazonas";
    }
  },
  BA {
    @Override
    public String getDescricao() {
      return "Bahia";
    }
  },
  CE {
    @Override
    public String getDescricao() {
      return "Ceará";
    }
  },
  DF {
    @Override
    public String getDescricao() {
      return "Distrito Federal";
    }
  },
  ES {
    @Override
    public String getDescricao() {
      return "Espírito Santo";
    }
  },
  GO {
    @Override
    public String getDescricao() {
      return "Goiás";
    }
  },
  MA {
    @Override
    public String getDescricao() {
      return "Maranhão";
    }
  },
  MT {
    @Override
    public String getDescricao() {
      return "Mato Grosso";
    }
  },
  MS {
    @Override
    public String getDescricao() {
      return "Mato Grosso do Sul";
    }
  },
  MG {
    @Override
    public String getDescricao() {
      return "Minas Gerais";
    }
  },
  PA {
    @Override
    public String getDescricao() {
      return "Pará";
    }
  },
  PB {
    @Override
    public String getDescricao() {
      return "Paraíba";
    }
  },
  PR {
    @Override
    public String getDescricao() {
      return "Paraná";
    }
  },
  PE {
    @Override
    public String getDescricao() {
      return "Pernambuco";
    }
  },
  PI {
    @Override
    public String getDescricao() {
      return "Piauí";
    }
  },
  RJ {
    @Override
    public String getDescricao() {
      return "Rio de Janeiro";
    }
  },
  RN {
    @Override
    public String getDescricao() {
      return "Rio Grande do Norte";
    }
  },
  RS {
    @Override
    public String getDescricao() {
      return "Rio Grande do Sul";
    }
  },
  RO {
    @Override
    public String getDescricao() {
      return "Rondônia";
    }
  },
  RR {
    @Override
    public String getDescricao() {
      return "Roraima";
    }
  },
  SC {
    @Override
    public String getDescricao() {
      return "Santa Catarina";
    }
  },
  SP {
    @Override
    public String getDescricao() {
      return "São Paulo";
    }
  },
  SE {
    @Override
    public String getDescricao() {
      return "Sergipe";
    }
  },
  TO {
    @Override
    public String getDescricao() {
      return "Tocantins";
    }
  };

  public abstract String getDescricao();
}
